
package io.rdplat.modules.oss.controller;

import com.google.gson.Gson;
import io.rdplat.common.exception.RRException;
import io.rdplat.common.utils.ConfigConstant;
import io.rdplat.common.utils.Constant;
import io.rdplat.common.utils.PageUtils;
import io.rdplat.common.utils.R;
import io.rdplat.common.validator.ValidatorUtils;
import io.rdplat.common.validator.group.AliyunGroup;
import io.rdplat.common.validator.group.QcloudGroup;
import io.rdplat.common.validator.group.QiniuGroup;
import io.rdplat.modules.oss.cloud.CloudStorageConfig;
import io.rdplat.modules.oss.cloud.OSSFactory;
import io.rdplat.modules.oss.entity.SysOssEntity;
import io.rdplat.modules.oss.service.SysOssService;
import io.rdplat.modules.sys.service.SysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysOssService.queryPage(params);

		return R.ok().put("page", page);
	}


    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@PostMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}else if (config.getType()==Constant.CloudService.FASTDFS.getValue()){
			//校验fastDfs

		}

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@PostMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.insert(ossEntity);
		return R.ok().put("url", url);
	}


	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody Long[] ids){
		List<SysOssEntity> sysOssEntities = sysOssService.selectBatchIds(Arrays.asList(ids));
		sysOssEntities.forEach(sys->{
			String url = sys.getUrl();
			OSSFactory.build(url).delete(url);
		});
		sysOssService.deleteBatchIds(Arrays.asList(ids));
		return R.ok();
	}

	@GetMapping("/download")
	public void download(String ids) throws Exception {
		String[] split = ids.split(",");
		List<SysOssEntity> sysOssEntities = sysOssService.selectBatchIds(Arrays.asList(split));
		StringBuilder builder = new StringBuilder();
		sysOssEntities.forEach(sys->{
			builder.append(sys.getUrl()).append(",");
		});
		String substring = builder.substring(0, builder.length() - 1);
		OSSFactory.build(sysOssEntities.get(0).getUrl()).download(substring);
	}

}

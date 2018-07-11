package io.rdplat.modules.oss.cloud;


import io.rdplat.common.utils.ConfigConstant;
import io.rdplat.common.utils.Constant;
import io.rdplat.common.utils.SpringContextUtils;
import io.rdplat.modules.sys.service.SysConfigService;

/**
 * 文件上传Factory
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 10:18
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        }
        return null;
    }


    public static CloudStorageService build(String path) {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
        if (path.contains(config.getAliyunDomain())) {
            return new AliyunCloudStorageService(config);
        } else if (path.contains(config.getQcloudDomain())) {
            return new QcloudCloudStorageService(config);
        } else if (path.contains(config.getQiniuDomain())) {
            return new QiniuCloudStorageService(config);
        }
        return null;
    }

}

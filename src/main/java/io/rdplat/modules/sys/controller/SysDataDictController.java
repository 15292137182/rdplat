package io.rdplat.modules.sys.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.rdplat.common.utils.PageUtils;
import io.rdplat.common.utils.R;
import io.rdplat.modules.sys.entity.SysDataDictEntity;
import io.rdplat.modules.sys.entity.SysMenuEntity;
import io.rdplat.modules.sys.service.SysDataDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author tiehu
 * @email tiehuwen@163.com
 * @date 2018-07-09 10:17:08
 */
@RestController
@RequestMapping("sys/dataDict")
public class SysDataDictController {
    @Autowired
    private SysDataDictService sysDataDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:dataDict:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = sysDataDictService.queryPage(params);
        List<SysDataDictEntity> list = (List<SysDataDictEntity>)page.getList();
        list.forEach(li->{
            if (li.getParentId()!=null) {
                SysDataDictEntity parent_id = sysDataDictService.selectOne(new EntityWrapper<SysDataDictEntity>().eq("dict_id", li.getParentId()));
                li.setParentName(parent_id.getName());
            }
        });
        return R.ok().put("page", page);
    }

    @GetMapping("select")
    @RequiresPermissions("sys:dataDict:select")
    public R select() {
        return R.ok().put("dictList", sysDataDictService.selectList(new EntityWrapper<SysDataDictEntity>().isNull("parent_id")));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dictId}")
    @RequiresPermissions("sys:dataDict:info")
    public R info(@PathVariable("dictId") String dictId) {
        SysDataDictEntity sysDataDict = sysDataDictService.selectById(dictId);

        return R.ok().put("sysDataDict", sysDataDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:dataDict:save")
    public R save(@RequestBody SysDataDictEntity sysDataDict) {
        sysDataDict.create();
        sysDataDictService.insert(sysDataDict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:dataDict:update")
    public R update(@RequestBody SysDataDictEntity sysDataDict) {
        sysDataDictService.updateById(sysDataDict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:dataDict:delete")
    public R delete(@RequestBody String[] dictIds) {
        sysDataDictService.deleteBatchIds(Arrays.asList(dictIds));

        return R.ok();
    }

}

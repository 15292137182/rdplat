package io.rdplat.modules.operate.controller;

import io.rdplat.common.utils.PageUtils;
import io.rdplat.common.utils.R;
import io.rdplat.modules.operate.entity.NoticeEntity;
import io.rdplat.modules.operate.service.NoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 运营_公告
 *
 * @author tiehu
 * @email tiehuwen@163.com
 * @date 2018-07-19 11:13:20
 */
@RestController
@RequestMapping("operate/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("operate:notice:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = noticeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{pId}")
    @RequiresPermissions("operate:notice:info")
    public R info(@PathVariable("pId") Long pId) {
        NoticeEntity notice = noticeService.selectById(pId);

        return R.ok().put("notice", notice);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("operate:notice:save")
    public R save(@RequestBody NoticeEntity notice) {
        notice.create();
        noticeService.insert(notice);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @RequiresPermissions("operate:notice:update")
    public R update(@RequestBody NoticeEntity notice) {
        notice.modify();
        noticeService.updateById(notice);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("operate:notice:delete")
    public R delete(@RequestBody Long[] pIds) {
        noticeService.deleteBatchIds(Arrays.asList(pIds));

        return R.ok();
    }

}

package io.rdplat.modules.operate.service;

import com.baomidou.mybatisplus.service.IService;
import io.rdplat.common.utils.PageUtils;
import io.rdplat.modules.operate.entity.NoticeEntity;

import java.util.Map;

/**
 * 运营_公告
 *
 * @author tiehu
 * @email tiehuwen@163.com
 * @date 2018-07-19 11:13:20
 */
public interface NoticeService extends IService<NoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


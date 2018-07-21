package io.rdplat.modules.operate.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.rdplat.common.utils.PageUtils;
import io.rdplat.common.utils.Query;

import io.rdplat.modules.operate.dao.NoticeDao;
import io.rdplat.modules.operate.entity.NoticeEntity;
import io.rdplat.modules.operate.service.NoticeService;


@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeDao, NoticeEntity> implements NoticeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NoticeEntity> page = this.selectPage(
                new Query<NoticeEntity>(params).getPage(),
                new EntityWrapper<NoticeEntity>()
        );

        return new PageUtils(page);
    }

}

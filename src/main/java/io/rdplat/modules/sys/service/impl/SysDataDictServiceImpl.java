package io.rdplat.modules.sys.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.rdplat.modules.sys.dao.SysDataDictDao;
import io.rdplat.modules.sys.entity.SysDataDictEntity;
import io.rdplat.modules.sys.service.SysDataDictService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.rdplat.common.utils.PageUtils;
import io.rdplat.common.utils.Query;


@Service("sysDataDictService")
public class SysDataDictServiceImpl extends ServiceImpl<SysDataDictDao, SysDataDictEntity> implements SysDataDictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysDataDictEntity> page = this.selectPage(
                new Query<SysDataDictEntity>(params).getPage(),
                new EntityWrapper<SysDataDictEntity>()
        );

        return new PageUtils(page);
    }

}

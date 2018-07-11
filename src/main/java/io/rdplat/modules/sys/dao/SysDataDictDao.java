package io.rdplat.modules.sys.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.rdplat.modules.sys.entity.SysDataDictEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 * 
 * @author tiehu
 * @email tiehuwen@163.com
 * @date 2018-07-09 10:17:08
 */
@Mapper
public interface SysDataDictDao extends BaseMapper<SysDataDictEntity> {
	
}

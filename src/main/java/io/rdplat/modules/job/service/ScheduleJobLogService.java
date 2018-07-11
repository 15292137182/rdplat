package io.rdplat.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import io.rdplat.common.utils.PageUtils;
import io.rdplat.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}

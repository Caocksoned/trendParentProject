package cn.caojie.trend.util;

import java.util.List;

import cn.hutool.core.date.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.caojie.trend.pojo.Index;
import cn.caojie.trend.service.IndexDataService;
import cn.caojie.trend.service.IndexService;

public class IndexDataSyncJob extends QuartzJobBean {
	
	@Autowired
	private IndexService indexService;

	@Autowired
	private IndexDataService indexDataService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("定时启动：" + DateUtil.now());
		List<Index> indexes = indexService.fresh();
		for (Index index : indexes) {
			 indexDataService.fresh(index.getCode());
		}
		System.out.println("定时结束：" + DateUtil.now());

	}

}

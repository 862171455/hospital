package org.java.util;

import org.java.service.HisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by GD on 2019/8/29 0029 13:17
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class StaticTask {
	@Autowired
	private HisService hisService;
	//3.添加定时任务
	@Scheduled(cron = "0 0 0 */1 * ?")
	//或直接指定时间间隔，例如：5秒
	//@Scheduled(fixedRate=5000)
	private void configureTasks() {
		System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
		hisService.addday();
	}
}

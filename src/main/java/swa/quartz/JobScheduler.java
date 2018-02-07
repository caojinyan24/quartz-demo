package swa.quartz;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;
import swa.job.JobMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by jinyan on 2/5/18 1:58 PM.
 */
@Component
@SuppressWarnings("unchecked")
public class JobScheduler {
    private static final Logger logger = LoggerFactory.getLogger(JobScheduler.class);
    private static final String GROUP = "swa";
    @Resource
    private JobMapper jobMapper;
    @Resource
    private BeanFactory beanFactory;
    @Resource
    private JobFactory jobFactory;
    private static Scheduler scheduler;


    @PostConstruct
    public void setUp() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.setJobFactory(jobFactory);
            loadJobs();
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void restart(swa.job.Job job) throws SchedulerException {
        scheduler.deleteJob(new JobKey(job.getBeanName(), GROUP));
        if (!Strings.isNullOrEmpty(job.getCronParam())) {
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends org.quartz.Job>) beanFactory.getBean(job.getBeanName()).getClass())
                    .withIdentity(new JobKey(job.getBeanName(), GROUP))
                    .setJobData(convertJson(job.getExecuteParam()))
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronParam()))
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        }

    }

    private void loadJobs() {
        try {
            List<swa.job.Job> jobs = jobMapper.selectDatas(null);
            for (swa.job.Job jobData : jobs) {
                if (!Strings.isNullOrEmpty(jobData.getCronParam())) {
                    JobDetail job = JobBuilder.newJob((Class<? extends org.quartz.Job>) beanFactory.getBean(jobData.getBeanName()).getClass())
                            .withIdentity(new JobKey(jobData.getBeanName(), GROUP))
                            .setJobData(convertJson(jobData.getExecuteParam()))
                            .build();
                    Trigger trigger = TriggerBuilder.newTrigger()
                            .startNow()
                            .withSchedule(CronScheduleBuilder.cronSchedule(jobData.getCronParam()))
                            .build();
                    scheduler.scheduleJob(job, trigger);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private JobDataMap convertJson(String jsonStr) {
        Map<String, String> result = Maps.newHashMap();
        if (!Strings.isNullOrEmpty(jsonStr)) {
            result = JSON.parseObject(jsonStr, Map.class);
        }
        return new JobDataMap(result);
    }


}

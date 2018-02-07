package swa.job;


import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import swa.quartz.QuartzJob;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by jinyan on 2/2/18 11:08 AM.
 */
@Component
public class DemoJob extends swa.job.BaseJob implements swa.job.JobInterface {
    private static final Logger logger = LoggerFactory.getLogger(DemoJob.class);

    @Resource
    private JobMapper jobMapper;

    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    protected Boolean doJobTask(Date scheduleTime) {
        return null;
    }

    protected Job getJobInfo() {
        return null;
    }

    @QuartzJob
    public void executeJob(Long jobId, String executeParam) {

    }
}

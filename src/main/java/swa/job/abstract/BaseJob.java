package swa.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by jinyan on 2/5/18 3:46 PM.
 */
@Component
public abstract class BaseJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(BaseJob.class);

    @Resource
    private JobMapper jobMapper;

    /**
     * 做具体的业务逻辑
     *
     * @param scheduleTime 调度时间
     * @return 执行是否成功
     */
    protected abstract Boolean doJobTask(Date scheduleTime);

    /**
     * 获取调度任务的信息
     *
     * @return beanName+methodName
     */
    protected abstract Job getJobInfo();

    /**
     * 目前一天只允许执行一次
     * 可以增加下次执行时间来处理需要一天执行多次的任务
     *
     * @param scheduleTime 调度时间
     */
    protected void scheduleByQuartz(Date scheduleTime) {
        logger.info("quartz do sth");
    }

    protected void scheduleByHand(Date scheduleTime, Long jobId) {
        logger.info("quartz do sth");

    }


}

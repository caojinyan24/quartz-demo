package swa.job;

/**
 * Created by jinyan on 2/6/18 6:55 PM.
 */
public interface JobInterface {

    /**
     * 通过手工执行
     * 子类需要标注@QuartzJob注解
     *
     * @param jobId 调度任务的ID
     */
    void executeJob(Long jobId, String executeParam);
}

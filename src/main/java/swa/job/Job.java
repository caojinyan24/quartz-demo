package swa.job;

import java.util.Date;

/**
 * Created by jinyan on 2/3/18 11:22 PM.
 */
public class Job {
    private Long id;
    private String jobName;
    private String beanName;
    private String methodName;
    private String cronParam;
    private String executeParam;
    private Integer status;
    private Date createTime;
    private Date updateTime;

    public Job() {
    }

    public Job(String beanName, String methodName) {
        this.beanName = beanName;
        this.methodName = methodName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public String getExecuteParam() {
        return executeParam;
    }

    public void setExecuteParam(String executeParam) {
        this.executeParam = executeParam;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getCronParam() {
        return cronParam;
    }

    public void setCronParam(String cronParam) {
        this.cronParam = cronParam;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Job{");
        sb.append("beanName='").append(beanName).append('\'');
        sb.append(", methodName='").append(methodName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

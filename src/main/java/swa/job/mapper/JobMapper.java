package swa.job.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import swa.job.Job;

import java.util.List;

/**
 * Created by jinyan on 2/3/18 11:22 PM.
 */
@Repository
public interface JobMapper {
    Long insertData(@Param("job") Job job);

    /**
     * 传空代表查询所有的数据
     * @param jobids
     * @return
     */
    List<Job> selectDatas(List<Long> jobids);

    Job selectData(@Param("beanName") String beanName, @Param("methodName") String methodName);

    Job selectDataById(@Param("id") Long id);

    void update(Job job);
}

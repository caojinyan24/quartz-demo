package swa.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import swa.quartz.QuartzJob;

import java.lang.reflect.Method;

/**
 * Created by jinyan on 10/11/17 9:53 PM.
 */
@Component
public class BeanParser implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(BeanParser.class);
//    @Resource
//    private JobMapper jobMapper;

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            if (null != method.getAnnotation(QuartzJob.class)) {
//                if (null == jobMapper.selectData(beanName, method.getName()))
//                    try {
//                        jobMapper.insertData(new Job(beanName, method.getName()));
//                    } catch (Exception e) {
//                        logger.info("add job info error");
//                    }
                                        logger.info("add job info error");

            }
        }
        return bean;
    }

}

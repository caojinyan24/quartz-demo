package swa.job;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import swa.quartz.JobScheduler;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * Created by jinyan on 2/3/18 11:52 PM.
 */
@Controller
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    @Resource
    private JobMapper jobMapper;
    @Resource
    private BeanFactory beanFactory;
    @Resource
    private JobScheduler jobScheduler;

    @RequestMapping("/index")
    public ModelAndView test() {
        ModelAndView result = new ModelAndView("index");
        result.addObject("jobList", jobMapper.selectDatas(null));
        return result;
    }

    @RequestMapping("/executeNow")
    @ResponseBody
    public String executeNow(@RequestParam("jobId") final Long jobId, @RequestParam("executeParam") final String executeParam) {
        final Job job = jobMapper.selectDataById(jobId);
        if (!Strings.isNullOrEmpty(job.getBeanName())) {
            Method[] methods = beanFactory.getBean(job.getBeanName()).getClass().getMethods();
            for (final Method method : methods) {
                if (method.getName().equals(job.getMethodName())) {
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                method.invoke(beanFactory.getBean(job.getBeanName()), Lists.newArrayList(jobId, executeParam).toArray());
                            } catch (Exception e) {
                                logger.error("invoke error:", e);
                            }
                        }
                    }).start();
                }
            }
        }
        return "success";
    }

    @RequestMapping("/modifyCron")
    @ResponseBody
    public String modifyCron(Job job) {
        try {
            jobMapper.update(job);
            jobScheduler.restart(job);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }


}

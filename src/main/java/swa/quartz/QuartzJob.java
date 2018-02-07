package swa.quartz;

import java.lang.annotation.*;

/**
 * Created by jinyan on 2/3/18 12:01 AM.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface QuartzJob {
}

package com.relaxed.common.log.access.annotation;

import com.relaxed.common.log.access.AccessLogAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Hccake
 * @version 1.0
 * @date 2019/10/15 18:11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ AccessLogAutoConfiguration.class })
public @interface EnableAccessLog {

}

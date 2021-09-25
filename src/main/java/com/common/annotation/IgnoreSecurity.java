package com.common.annotation;

import java.lang.annotation.*;

/**
 * 忽略登录检查
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreSecurity {
}

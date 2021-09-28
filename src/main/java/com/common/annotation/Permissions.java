package com.common.annotation;

import java.lang.annotation.*;

/**
 * 权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permissions {
    String[] value();
}

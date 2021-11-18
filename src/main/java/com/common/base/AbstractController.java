package com.common.base;

import org.basis.framework.security.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 *  Controller公共组件
 * @Author ChenWenJie
 * @Data 2021/10/28 3:00 下午
 **/
public abstract class AbstractController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected LoginUser getUser() {
        return BaseUtil.getUser();
    }

    protected Long getUserId() {
        return BaseUtil.getUserId();
    }
}

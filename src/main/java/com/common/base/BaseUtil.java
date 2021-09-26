package com.common.base;

import com.common.converter.AdminUserChannelLoginUser;
import com.admin.entity.AdminUser;
import org.basis.framework.base.AbstractBaseUtil;
import org.basis.framework.security.LoginUser;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/9/24 10:54 上午
 **/
public class BaseUtil extends AbstractBaseUtil {

    public static void setUser(AdminUser adminUser) {
        LoginUser loginUser = AdminUserChannelLoginUser.INSTANCE.adminUserToLoginUser(adminUser);
        setUser(loginUser);
    }
}

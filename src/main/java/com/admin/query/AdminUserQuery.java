package com.admin.query;

import com.admin.entity.AdminUser;
import com.admin.enums.UserStatusEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.basis.framework.query.Query;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/12/7 5:50 下午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class AdminUserQuery extends Query<AdminUser> {
    private LambdaQueryWrapper<AdminUser> wrapper = Wrappers.lambdaQuery();
    private Long userId ;
    private String account;
    private Integer status;
    private String mobile;
    private String userName;
    private Integer sex;
    private Integer deptId;
    @Override
    public Wrapper<AdminUser> toWrapper() {
        if (this.valid(userId)){
            wrapper.eq(AdminUser::getUserId,userId);
        }
        if (this.valid(account)) {
            wrapper.like(AdminUser::getAccount,account);
        }
        if (this.valid(status)) {
            wrapper.eq(AdminUser::getStatus,UserStatusEnum.valueOf(status));
        }
        if (this.valid(mobile)) {
            wrapper.like(AdminUser::getMobile,mobile);
        }
        if (this.valid(userName)) {
            wrapper.like(AdminUser::getUserName,userName);
        }
        if (this.valid(sex)) {
            wrapper.eq(AdminUser::getSex,sex);
        }
        if (this.valid(deptId)) {
            wrapper.eq(AdminUser::getDeptId,deptId);
        }
        return wrapper;
    }
}

package com.admin.query;

import com.admin.entity.AdminUserRole;
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
public class AdminUserRoleQuery extends Query<AdminUserRole> {
     private LambdaQueryWrapper wrapper = Wrappers.lambdaQuery();
    @Override
    public Wrapper<AdminUserRole> toWrapper() { return wrapper; }
}

package com.admin.query;

import com.admin.entity.AdminDept;
import com.admin.entity.AdminMenu;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.basis.framework.query.Query;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/12/7 4:50 下午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class AdminMenuQuery extends Query<AdminMenu> {
    private LambdaQueryWrapper<AdminMenu> wrapper = Wrappers.lambdaQuery();

    @Override
    public Wrapper<AdminMenu> toWrapper() { return wrapper; }
}

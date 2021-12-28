package com.admin.query;

import com.admin.entity.AdminDept;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.basis.framework.query.Query;

/**
 * @Description
 * @Author ChenWenJie
 * @Data 2021/12/6 5:52 下午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class AdminDeptQuery extends Query<AdminDept> {
    private Integer type;

    private LambdaQueryWrapper<AdminDept> wrapper = Wrappers.lambdaQuery();

    @Override
    public Wrapper<AdminDept> toWrapper() {
        if (this.valid(type)){
            wrapper.eq(AdminDept::getType,type);
        }
        return wrapper;
    }
}

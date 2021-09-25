package com.dao;

import com.entity.AdminUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * 用户角色关联表 Mapper 接口
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Mapper
public interface AdminUserRoleMapper extends BaseMapper<AdminUserRole> {

}

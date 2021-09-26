package com.admin.dao;

import com.admin.entity.AdminRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * 菜单角色关联表 Mapper 接口
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Mapper
public interface AdminRoleMenuMapper extends BaseMapper<AdminRoleMenu> {

}

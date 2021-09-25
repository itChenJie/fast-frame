package com.dao;

import com.entity.AdminMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 菜单 Mapper 接口
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Mapper
public interface AdminMenuMapper extends BaseMapper<AdminMenu> {

    List<AdminMenu> findAllByRole(Integer roleId);

    List<String> findAllParentRole(Integer roleId);
}

package com.admin.dao;

import com.admin.entity.AdminMenu;
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
    /**
     * 按角色查找全部
     * @param roleId
     * @return
     */
    List<AdminMenu> findAllByRole(Integer roleId);

    /**
     * 查找所有父角色
     * @param roleId
     * @return
     */
    List<String> findAllParentRole(Integer roleId);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<AdminMenu> queryListParentId(Integer parentId);

    List<Long> findUserMenuIds(Long userId);

    List<AdminMenu> findNotButtonList();

    List<AdminMenu> queryListParentIds(Collection<? extends Serializable> parentId);
}

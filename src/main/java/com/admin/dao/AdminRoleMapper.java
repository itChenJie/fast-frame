package com.admin.dao;

import com.admin.entity.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色 Mapper 接口
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    List<AdminRole> findAllByUserId(Long userId);
}

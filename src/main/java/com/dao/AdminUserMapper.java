package com.dao;

import com.entity.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户 Mapper 接口
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
@Mapper
public interface AdminUserMapper extends BaseMapper<AdminUser> {

   List<AdminUser> findByAccount(String account);
}

package com.common.converter;

import cn.hutool.core.collection.CollectionUtil;
import com.admin.entity.AdminMenu;
import com.admin.entity.AdminRole;
import com.admin.entity.AdminUser;
import org.basis.framework.security.LoginUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * AdminUser 转换成 LoginUser
 * @Author ChenWenJie
 * @Data 2021/9/24 11:09 上午
 **/
@Mapper
public interface AdminUserChannelLoginUser {
    AdminUserChannelLoginUser INSTANCE = Mappers.getMapper(AdminUserChannelLoginUser.class);

    @Mappings({
            @Mapping(source = "userId",target = "uid"),
            @Mapping(source = "userName",target = "name"),
            @Mapping(source = "img",target = "avatar"),
            @Mapping(source = "email",target = "email"),
            @Mapping(source = "mobile",target = "phone"),
            @Mapping(source = "dept.deptId",target = "deptId"),
            @Mapping(source = "adminRoles",target = "authoritys",qualifiedByName = "formatRoles")
    })
    LoginUser adminUserToLoginUser(AdminUser adminUser);

    @Named("formatRoles")
    default List<String> formatRoles(List<AdminRole> adminRoles){
        List<String> roles = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(adminRoles)){
            for (AdminRole adminRole : adminRoles) {
                if (CollectionUtil.isNotEmpty(adminRole.getAdminMenus())){
                    List<AdminMenu> adminMenus = adminRole.getAdminMenus();
                    roles.addAll(adminMenus.stream().map(item ->item.getPerms()).collect(Collectors.toList()));
                }
            }
        }
        return roles;
    }
}

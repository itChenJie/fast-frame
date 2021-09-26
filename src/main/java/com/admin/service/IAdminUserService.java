package com.admin.service;

import com.admin.enums.UserStatusEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.admin.entity.AdminUser;
import org.basis.framework.page.PageUtils;
import org.basis.framework.utils.R;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户 服务类
 *
 * @author ChenWenJie
 * @since 2021-07-04
 */
public interface IAdminUserService extends IService<AdminUser> {
    /**
    * 分页查询
    * @param params
    * @return
    */
    PageUtils queryPage(Map<String, Object> params);

    /**
    * 添加
    * @param adminUser
    */
    void addAdminUser(AdminUser adminUser);

    /**
    * 更新
    * @param adminUser
    */
    void updateAdminUser(AdminUser adminUser);

    /**
    * 删除
    * @param idList
    */
    void deleteAdminUser(Collection<? extends Serializable> idList);

    /**
     * 更新用户状态
     * @param userId
     * @param userStatus
     */
    void updateUserStatus(Long userId, UserStatusEnum userStatus);
    /**
    * id查询
    * @param id
    * @return
    */
    AdminUser findById(Long id);

    /**
     * 根据条件查找
     * @param wrapper
     * @return
     */
    default List<AdminUser> findAdminUserList(Wrapper<AdminUser> wrapper){
        return list(wrapper);
    }
    /**
     * 登陆
     * @param account
     * @param passWord
     * @return
     */
    R login(String account,String passWord);

    /**
     * 更新密码
     * @param account
     * @param oldPassWord
     * @param newPassWord
     * @return
     */
    R updatePassWord(String account,String oldPassWord,String newPassWord);

    /**
     * 重置密码
     * @param userId
     * @param passWord
     * @return
     */
    void resetPassWord(Long userId,String passWord);
}

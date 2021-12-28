package com.admin.service.impl;

import com.Application;
import com.admin.entity.AdminDept;
import com.admin.entity.AdminUser;
import com.admin.enums.UserStatusEnum;
import com.admin.query.AdminMenuQuery;
import com.admin.query.AdminUserQuery;
import com.admin.service.IAdminDeptService;
import com.admin.service.IAdminUserService;
import com.common.config.pool.AsyncPoolConfig;
import org.basis.framework.page.PageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@MapperScan(basePackages = "com.admin.dao")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("dev")
public class AdminUserServiceImplTest {
    @Autowired
    IAdminUserService adminUserService;

    @Autowired
    IAdminDeptService adminDeptService;

    @Autowired
    AsyncPoolConfig asyncPoolConfig;

    @Test
    public void queryPage() {
        for (int i = 0; i < 1; i++) {
            AdminUserQuery query = new AdminUserQuery();
            query.setPageIndex(0);
            query.setPageSize(10);
            PageUtils pageUtils = adminUserService.queryPage(query);
            List<AdminUser> list = (List<AdminUser>) pageUtils.getList();
            for (AdminUser adminUser:list){
                System.out.println(adminUser.toString());
            }
            System.out.println();
        }

    }

    @Test
    public void addAdminUser() {
        adminUserService.addAdminUser(AdminUser.builder()
                .account("15070158444")
                .userName("陈1111")
                .passWord("111")
                .sex(1)
                .status(UserStatusEnum.NORMAL)
                .build());
    }

    @Test
    public void findById(){
        AdminUser adminUser = adminUserService.findById(1234561l);
        System.out.println(adminUser);
    }


    @Test
    public void  treeDept(){
        List<AdminDept> deptTreeList = adminDeptService.findDeptTreeList(0);
        for (AdminDept adminDept : deptTreeList) {
            System.out.println( adminDept.toString());
        }
    }

    public static void main(String[] args) {
        AdminMenuQuery query = new AdminMenuQuery();
        System.out.println(query);
    }

}


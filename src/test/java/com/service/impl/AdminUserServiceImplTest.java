package com.service.impl;

import com.Application;
import com.common.config.pool.AsyncPoolConfig;
import com.entity.AdminUser;
import com.service.IAdminUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@MapperScan(basePackages = "com.dao")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("dev")
public class AdminUserServiceImplTest {
    @Autowired
    IAdminUserService adminUserService;
    @Autowired
    AsyncPoolConfig asyncPoolConfig;

    @Test
    public void queryPage() {
        for (int i = 0; i < 100; i++) {
            Map<String, Object> params = new HashMap<>();
            params.put("page","0");
            params.put("limit","10");
            adminUserService.queryPage(params);
        }

    }

    @Test
    public void addAdminUser() {
        adminUserService.addAdminUser(AdminUser.builder()
                .userId(1234560l)
                .account("1507015844")
                .userName("陈1111")
                .passWord("111")
                .sex(1)
                .build());


    }


}


package com.feignClient;

import com.admin.vo.LoginVo;
import com.common.feign.FeignConfigure;
import com.common.feign.FeignFallbackFactory;
import org.basis.framework.http.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description
 * Feign 连接
 *  configuration 配置本连接的配置信息
 *  fallbackFactory 熔断回调处理工厂
 *
 * @Author ChenWenJie
 * @Data 2021/12/28 3:40 下午
 **/
@FeignClient(name = "study-service",configuration = FeignConfigure.class,fallbackFactory = FeignFallbackFactory.class)
public interface AdminFeignServiceDemo {
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST, produces = "application/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    BaseResponse remoteLogin(@RequestBody LoginVo loginVo);

}

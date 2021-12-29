package com.common.feign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.basis.framework.http.BaseResponse;

/**
 * @Description
 *  feign 熔断回调处理工厂，默认处理接口异常下的默认值
 * @Author ChenWenJie
 * @Data 2021/12/29 12:06 下午
 **/
@Slf4j
public class FeignFallbackFactory implements FallbackFactory<BaseResponse> {

    @Override
    public BaseResponse create(Throwable throwable) {
        log.error("feign fallback; reason was: {}",throwable.getMessage());
        return new BaseResponse(500,"服务端异常！",null);
    }
}

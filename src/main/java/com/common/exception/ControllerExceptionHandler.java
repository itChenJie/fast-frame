package com.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.basis.framework.error.BizCodeEnume;
import org.basis.framework.error.DefinitionExceptionHandler;
import org.basis.framework.utils.R;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description
 *  统一异常程序
 * @Author ChenWenJie
 * @Data 2021/9/24 4:45 下午
 **/
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends DefinitionExceptionHandler {

    /**
     * 参数约束违规异常
     * @param e
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public R constraintViolationException(ConstraintViolationException e){
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            log.error("Parameter Violation Exception :{}",constraintViolation.getMessage());
            return R.error(BizCodeEnume.PARAM_VALIDATE_ERROR.getCode(),constraintViolation.getMessage());
        }
        return R.error(BizCodeEnume.PARAM_VALIDATE_ERROR.getCode(),BizCodeEnume.PARAM_VALIDATE_ERROR.getMsg());
    }
}

package com.xzq.base.exeception;

import com.xzq.base.common.BaseResponse;
import com.xzq.base.common.ErrorCode;
import com.xzq.base.common.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常管理器
 *
 * @author kongbai
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse BusinessExceptionHandler(BusinessException e){
        log.error("BusinessException:"+e.getMessage(),e);
        return ResultUtil.error(e.getCode(),e.getMessage(),e.getDescription());
    }
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse RuntimeExceptionHandler(BusinessException e){
        log.error("RuntimeException:",e);
        return ResultUtil.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }

}

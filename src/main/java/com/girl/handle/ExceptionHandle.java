package com.girl.handle;

import com.girl.domain.Result;
import com.girl.exception.GirlException;
import com.girl.units.ResultUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 所有controller 中抛出的异常 都进过这 包装处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUnit.error(girlException.getCode(), girlException.getMessage());
        }
        logger.error("【系统异常】 {}", e);
        return ResultUnit.error(-1, "未知错误");
    }
}

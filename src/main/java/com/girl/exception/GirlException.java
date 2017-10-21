package com.girl.exception;

import com.girl.enums.ResultEnum;

/**
 * 全局异常
 * 框架只对RunntimeException 处理 回滚
 */
public class GirlException extends RuntimeException {
    private int code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

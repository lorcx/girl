package com.girl.units;

import com.girl.domain.Result;

/**
 * http 请求返回工具类
 */
public class ResultUnit {
    private ResultUnit() throws Exception {
        throw new Exception("不能实例化");
    }

    /**
     * 成功
     * @param obj
     * @return
     */
    public static Result success(Object obj) {
        Result r = new Result();
        r.setCode(1);
        r.setMsg("成功");
        r.setData(obj);
        return r;
    }

    /**
     * 成功
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 失败
     * @param code
     * @param msg
     * @return
     */
    public static Result error(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}

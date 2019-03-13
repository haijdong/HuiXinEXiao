package com.synjones.common_base.net;

import java.io.Serializable;

/**
 * author  :  donghaijun
 * data    :  2019/3/8
 * version :  1.0
 * des     :  抽取的一个基类的bean, 直接在泛型中传data就行
 */
public class BaseHttpResult<T> implements Serializable {
    private static final long serialVersionUID = 2690553609250007325L;
    public static final int SUCCESS_CODE = 0;

    private int status;
    private String message;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 正常返回
     *
     * @return
     */
    public boolean isSuccessFul() {
        return getStatus() == SUCCESS_CODE;
    }

    @Override
    public String toString() {
        return "BaseHttpResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}

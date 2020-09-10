package com.baidu.shop.Exception;

/**
 * @ClassName MrException
 * @Description: TODO
 * @Author caoyaohui
 * @Date 2020/9/3
 * @Version V1.0
 **/
public class MrException extends RuntimeException{

    private Integer code;

    private String msg;

    public MrException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}

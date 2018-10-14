package com.example.tool.exception;

/**
 * Created with IntelliJ IDEA
 * Created By wzj
 * Date: 2018/10/13
 * Time: 14:32
 * Description:
 * Modified By:
 */
public class UnsupportedMethodException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766939L;


    public UnsupportedMethodException() {
        super();
    }


    public UnsupportedMethodException(String message) {
        super(message);
    }


    public UnsupportedMethodException(String message, Throwable cause) {
        super(message, cause);
    }

}

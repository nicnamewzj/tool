package com.example.tool.exception;

/**
 * Created with IntelliJ IDEA
 * Created By wzj
 * Date: 2018/10/13
 * Time: 19:03
 * Description:
 * Modified By:
 */
public class UnsupportedEncodingException extends RuntimeException {

    public UnsupportedEncodingException(){
        super();
    }

    public UnsupportedEncodingException(String message){
        super(message);
    }

    public UnsupportedEncodingException(String message, Throwable cause){
        super(message,cause);
    }

}

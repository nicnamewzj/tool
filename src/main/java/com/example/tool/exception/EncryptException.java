package com.example.tool.exception;

/**
 * Created with IntelliJ IDEA
 * Created By wzj
 * Date: 2018/10/13
 * Time: 18:16
 * Description:
 * Modified By:
 */
public class EncryptException extends RuntimeException {

    public EncryptException(){
        super();
    }

    public EncryptException(String message){
        super(message);
    }

    public EncryptException(String message, Throwable cause){
        super(message,cause);
    }
}

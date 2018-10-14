package com.example.tool.exception;

/**
 * Created with IntelliJ IDEA
 * Created By wzj
 * Date: 2018/10/13
 * Time: 18:24
 * Description:
 * Modified By:
 */
public class DecryptException extends RuntimeException {

    public DecryptException(){
        super();
    }

    public DecryptException(String message){
        super(message);
    }

    public DecryptException(String message,Throwable cause){
        super(message,cause);
    }
}

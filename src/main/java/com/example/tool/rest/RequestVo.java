package com.example.tool.rest;

import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA
 * Created By wzj
 * Date: 2018/10/13
 * Time: 14:03
 * Description:
 * Modified By:
 */
@Component
public class RequestVo {

    private String encryptKey;
    private String encryptIv;
    private String requestText;

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public String getEncryptIv() {
        return encryptIv;
    }

    public void setEncryptIv(String encryptIv) {
        this.encryptIv = encryptIv;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }
}

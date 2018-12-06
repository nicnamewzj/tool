package com.example.tool.service;

import com.example.tool.exception.DecryptException;
import com.example.tool.exception.EncryptException;
import com.example.tool.rest.RequestVo;
import com.example.tool.util.EncryptorAES;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.*;

import static java.lang.String.format;

/**
 * Created with IntelliJ IDEA
 * Created By wzj
 * Date: 2018/10/13
 * Time: 13:57
 * Description:
 * Modified By:
 */
@Service
public class DataDealService {

    @Value("${spring.thymeleaf.encoding}")
    private String encoding;

    //加签
    public String dataMD5(String json) throws UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex(json.getBytes(encoding));
    }

    //Base64 加密
    public String dataEncrypt(RequestVo requestVo) {
        try {
            return EncryptorAES.encryptToBase64(requestVo.getRequestText(), encoding, requestVo.getEncryptKey(), requestVo.getEncryptIv());
        } catch (Exception e) {
            throw new EncryptException(format("Fail to encrypt request %s", requestVo.getRequestText()), e);
        }
    }

    //Base64 解密
    public String dataDecrypt(RequestVo requestVo) {
        try {
            return EncryptorAES.decryptFromBase64(requestVo.getRequestText(), encoding, requestVo.getEncryptKey(), requestVo.getEncryptIv());
        } catch (Exception e) {
            throw new DecryptException(format("Fail to decrypt request %s", requestVo.getRequestText()), e);
        }
    }

}

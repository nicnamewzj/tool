package com.example.tool.controller;

import com.example.tool.util.EncryptorAES;
import org.springframework.http.MediaType;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/json")
public class JsonConvertController {

    private final static String ENCODING = "UTF-8";

    @RequestMapping("/MD5")
    public String MD5(@RequestBody String json) throws UnsupportedEncodingException {
        return DigestUtils.md5DigestAsHex(json.getBytes(ENCODING));
    }

    /**
     * @param sSrc           待加密的数据
     * @param encodingFormat 编码格式
     * @param sKey           key(16位)
     * @param ivParameter    偏移量
     * @return string
     * @throws Exception Description:  AES-128-CBC 加密
     */
    @RequestMapping(path = "encrypt/{encoding-format}/{s-key}/{iv-parameter}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String encrypt(@RequestBody String sSrc,
                          @PathVariable("encoding-format") String encodingFormat,
                          @PathVariable("s-key") String sKey,
                          @PathVariable("iv-parameter") String ivParameter) throws Exception {
        return EncryptorAES.encryptToBase64(sSrc, encodingFormat, sKey, ivParameter);
    }

    /**
     * @param sSrc           待解密的数据
     * @param encodingFormat 编码格式
     * @param sKey           key(16位)
     * @param ivParameter    偏移量
     * @return json
     * @throws Exception Description:  AES-128-CBC 解密
     */
    @RequestMapping(path = "dencrypt/{encoding-format}/{s-key}/{iv-parameter}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String dencrypt(@RequestBody String sSrc,
                           @PathVariable("encoding-format") String encodingFormat,
                           @PathVariable("s-key") String sKey,
                           @PathVariable("iv-parameter") String ivParameter) throws Exception {
        return EncryptorAES.decryptFromBase64(sSrc, encodingFormat, sKey, ivParameter);
    }

}

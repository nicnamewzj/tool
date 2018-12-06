package com.example.tool.controller;

import com.example.tool.enumType.Method;
import com.example.tool.exception.UnsupportedMethodException;
import com.example.tool.rest.RequestVo;
import com.example.tool.service.DataDealService;
import com.example.tool.util.EncryptorAES;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static java.lang.String.format;

@Controller
@RequestMapping("/")
public class DataConvertController {

    @Autowired
    DataDealService dataDealService;

    @Value("${spring.thymeleaf.encoding}")
    private String encoding;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index.html")
    public String index2() {
        return "index";
    }

    @RequestMapping(value = "/base64/{method}", method = RequestMethod.POST)
    @ResponseBody
    public String MD5(@RequestBody RequestVo requestVo,
                      @PathVariable("method") String method) throws UnsupportedEncodingException {
        String result = "";
        final String encryptKey = new String(Base64.getDecoder().decode(requestVo.getEncryptKey()), encoding);
        final String encryptIv = new String(Base64.getDecoder().decode(requestVo.getEncryptIv()), encoding);
        requestVo.setEncryptKey(encryptKey);
        requestVo.setEncryptIv(encryptIv);
        switch ( Method.valueOf(method)) {
            case md5:
                result = dataDealService.dataMD5(requestVo.getRequestText());
                break;
            case encrypt:
                result = dataDealService.dataEncrypt(requestVo);
                break;
            case decrypt:
                result = dataDealService.dataDecrypt(requestVo);
                break;
            default:
                throw new UnsupportedMethodException(format("Unsupported method: %s",method));

        }
        return result;

    }
    @RequestMapping(value = "/file/md5",method = RequestMethod.POST)
    @ResponseBody
    public String fileMd5(@RequestParam("file") MultipartFile file) throws IOException {
        InputStream isp = file.getInputStream();
        final String hex = DigestUtils.md5Hex(isp);
        System.out.println("Sign result :" + hex);
        return hex;
    }
    /**
     * @param sSrc           待加密的数据
     * @param encodingFormat 编码格式
     * @param sKey           key(16位)
     * @param ivParameter    偏移量
     * @return string
     * @throws Exception Description:  AES-128-CBC 加密
     */
    @RequestMapping(path = "encrypt/{encoding-format}/{s-key}/{iv-parameter}", method = RequestMethod.POST)
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

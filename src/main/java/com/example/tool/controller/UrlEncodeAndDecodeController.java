package com.example.tool.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA
 * Created By wzj
 * Date: 2018/10/11
 * Time: 19:09
 * Description:
 * Modified By:
 */
@Controller
@RequestMapping("/urlCode")
public class UrlEncodeAndDecodeController {

    @RequestMapping(value = "/encode/{encoding-format}",method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String encode(@RequestParam("request-param") String requestParam,
                         @PathVariable("encoding-format") String encodingFormat) throws UnsupportedEncodingException {
        return URLEncoder.encode(requestParam, encodingFormat);
    }

    @RequestMapping(path = "/decode/{encoding-format}")
    public String decode(@RequestParam("request-param") String requestParam,
                         @PathVariable("encoding-format") String encodingFormat) throws UnsupportedEncodingException {
        return URLDecoder.decode(requestParam, encodingFormat);
    }
}

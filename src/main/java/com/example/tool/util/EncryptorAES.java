package com.example.tool.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created with IntelliJ IDEA
 * Created By zcc
 * Date: 2018/6/1
 * Time: 14:43
 * Description:  AES-128-CBC 加解密
 */
public class EncryptorAES {

    private static final String ALGORITHM = "AES";
    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * @param sSrc           待加密的数据
     * @param encodingFormat 编码格式
     * @param sKey           key(16位)
     * @param ivParameter    偏移量
     * @return
     * @throws Exception Description:  AES-128-CBC 加密
     */
    public static String encryptToBase64(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        byte[] encryptedData = encrypt(sSrc.getBytes(encodingFormat), sKey.getBytes(encodingFormat), ivParameter.getBytes(encodingFormat));
        return new String(Base64.getEncoder().encode(encryptedData), encodingFormat);
    }

    /**
     * @param sSrc           待解密的数据
     * @param encodingFormat 编码格式
     * @param sKey           key(16位)
     * @param ivParameter    偏移量
     * @return
     * @throws Exception Description:  AES-128-CBC 解密
     */
    public static String decryptFromBase64(String sSrc, String encodingFormat, String sKey, String ivParameter) throws Exception {
        //先用base64解密
        byte[] encryptedData = Base64.getDecoder().decode(sSrc);
        byte[] decryptedData = decrypt(encryptedData, sKey.getBytes(encodingFormat), ivParameter.getBytes(encodingFormat));
        return new String(decryptedData, encodingFormat);
    }

    /**
     * 加密
     *
     * @param data
     * @param key
     * @param iv
     * @return
     */
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) {
        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid AES key length (must be 16 bytes)");
        }

        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("encrypt failed", e);
        }
    }


    /**
     * 解密
     *
     * @param data
     * @param key
     * @param iv
     * @return
     */

    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) {
        if (key.length != 16) {
            throw new IllegalArgumentException("Invalid AES key length (must be 16 bytes)");
        }

        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("decrypt failed", e);
        }
    }

}

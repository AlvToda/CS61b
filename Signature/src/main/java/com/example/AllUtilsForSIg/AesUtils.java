package com.example.AllUtilsForSIg;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AesUtils {

    //static final String ALGORITHM = "AES";

    public static SecretKey generateKey() throws NoSuchAlgorithmException { // 生成密钥
        KeyGenerator secretGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        secretGenerator.init(secureRandom);
        SecretKey secretKey = secretGenerator.generateKey();
        System.out.println(Arrays.toString(secretKey.getEncoded()));
        return secretKey;
    }

    static Charset charset = Charset.forName("UTF-8");
    public static byte[] encrypt(String content, SecretKey secretKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { // 加密
        return aes(content.getBytes(charset),Cipher.ENCRYPT_MODE,secretKey);
    }

    public static String decrypt(byte[] contentArray, SecretKey secretKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { // 解密
        byte[] result =  aes(contentArray,Cipher.DECRYPT_MODE,secretKey);
        return new String(result,charset);
    }

    private static byte[] aes(byte[] contentArray, int mode, SecretKey secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(mode, secretKey);
        byte[] result = cipher.doFinal(contentArray);
        return result;
    }

    public static void main(String[] args) {
        String content = "AES算法测试文本";
        SecretKey secretKey;
        try {
            long timeStart = System.currentTimeMillis();
            secretKey = generateKey();
            byte[] encryptResult = encrypt(content, secretKey);
            long timeEnd = System.currentTimeMillis();
            System.out.println("加密后的结果为：" + new String(encryptResult,charset));
            String decryptResult = decrypt(encryptResult,secretKey);
            System.out.println("解密后的结果为：" + decryptResult);
            System.out.println("加密用时：" + (timeEnd - timeStart));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }


}

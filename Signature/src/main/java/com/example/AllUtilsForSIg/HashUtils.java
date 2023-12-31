package com.example.AllUtilsForSIg;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    private static String ToHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            //java.lang.Integer.toHexString() 方法的参数是int(32位)类型，
            //如果输入一个byte(8位)类型的数字，这个方法会把这个数字的高24为也看作有效位，就会出现错误
            //如果使用& 0XFF操作，可以把高24位置0以避免这样错误
            String temp = Integer.toHexString(b & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }

    private static String getSha(String plainText) {
        //输入的字符串转换成字节数组
        byte[] bytes = plainText.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest;
        try {
            //获得SHA512转换器
            messageDigest = MessageDigest.getInstance("SHA-512");
            //bytes是输入字符串转换得到的字节数组
            messageDigest.update(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA签名过程中出现错误,算法异常");
        }
        //转换并返回结果，也是字节数组，包含16个元素
        byte[] digest = messageDigest.digest();
        //字符数组转换成字符串返回
        String result = ToHexString(digest);
        //转换大写
        return result;
    }

    public static void main(String[] args) {
        String plainText="Alianasjksfggqwigwfafgdskfjegwfifsdjkfsgfsg";
        String p2="Alianasjksfggqwigwfafgdskfjegwfifsdjkfsgfsg";
        String p3="ALINUGF";

        String r1=getSha(plainText);
        String r2=getSha(p2);
        String r3=getSha(p3);
        System.out.println("SHA512加密结果："+r1);
        System.out.println(r1.equals(r2));
        System.out.println("r3："+r3);
        System.out.println(r1.equals(r3));

    }
}

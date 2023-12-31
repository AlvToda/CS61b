package com.example.AllUtilsForSIg;

import java.math.BigInteger;
import java.util.Random;

public class RSAUtils {
    private final static int numLength = 1024;//素数长度
    private final static int accuracy = 100;//素数的准确率为1-(2^(-accuracy))
    private static Keys k;
    //密匙对
    static class Keys {
        BigInteger n, e, d;

        public Keys(BigInteger n, BigInteger e, BigInteger d) {
            this.n = n;
            this.e = e;
            this.d = d;
        }

        public PublicKey getPublicKey() {
            return new PublicKey(n, e);
        }

        public PrivateKey getPrivateKey() {
            return new PrivateKey(n, d);
        }

        //密钥
        static class PrivateKey {
            public BigInteger n, d;

            public PrivateKey(BigInteger n, BigInteger d) {
                this.n = n;
                this.d = d;
            }
        }

        //公钥
        static class PublicKey {
            public BigInteger n, e;

            public PublicKey(BigInteger n, BigInteger e) {
                this.n = n;
                this.e = e;
            }
        }
    }

    //产生两个随机1024位大质数
    public static BigInteger[] getRandomPQ() {
        BigInteger p = BigInteger.probablePrime(numLength, new Random());
        while (!p.isProbablePrime(accuracy)) {
            p = BigInteger.probablePrime(numLength, new Random());
        }
        BigInteger q = BigInteger.probablePrime(numLength, new Random());
        while (!q.isProbablePrime(accuracy)) {
            q = BigInteger.probablePrime(numLength, new Random());
        }
        return new BigInteger[]{p, q};
    }

    //扩展欧几里得方法,计算 ax + by = 1中的x与y的整数解（a与b互质）
    private static BigInteger[] extGCD(BigInteger a, BigInteger b) {
        if (b.signum() == 0) {
            return new BigInteger[]{a, new BigInteger("1"), new BigInteger("0")};
        } else {
            BigInteger[] bigIntegers = extGCD(b, a.mod(b));
            BigInteger y = bigIntegers[1].subtract(a.divide(b).multiply(bigIntegers[2]));
            return new BigInteger[]{bigIntegers[0], bigIntegers[2], y};
        }
    }


    public static Keys generateKey(BigInteger p, BigInteger q) {
        BigInteger[] pq = new BigInteger[2];
        //令n = p * q。取 φ(n) = (p-1) * (q-1)。
        BigInteger n = p.multiply(q);
        //取 φ(n) = (p-1) * (q-1)。
        BigInteger fy = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        //取e为65537
        BigInteger e = new BigInteger("65537");
        //计算ed与fy的模反元素d。令 ed mod φ(n)  = 1，计算d，然后将( n , d ) 作为私钥对
        BigInteger[] bigIntegers = extGCD(e, fy);
        //计算出的d不能是负数，如果是负数，则进行加上一个fy
        BigInteger d = bigIntegers[1];
        if (d.signum() == -1) {
            d = d.add(fy);
        }
        //返回计算出的密钥
        k=new Keys(n, e, d);
        return k;

    }
    //由于加密和解密都是相似的，因此使用一个函数实现这一段逻辑
    //(base * exp) % mod =(base % mod) *(exp % mod) % mod
    private static BigInteger En_De_Mod(BigInteger base, BigInteger exp, BigInteger mod) {

        BigInteger res = BigInteger.ONE;

        BigInteger tempBase = new BigInteger(base.toString());

        for (int i = 0; i < exp.bitLength(); i++) {
            if (exp.testBit(i)) {//判断对应二进制位是否为1
                res = (res.multiply(tempBase)).mod(mod);
            }
            tempBase = tempBase.multiply(tempBase).mod(mod);
        }
        return res;

    }

    public static BigInteger encrypt(BigInteger text, Keys.PublicKey publicKey) {
        return En_De_Mod(text, publicKey.e, publicKey.n);
    }

    //解密
    public static BigInteger decrypt(BigInteger cipher, Keys.PrivateKey privateKey) {
        return En_De_Mod(cipher, privateKey.d, privateKey.n);
    }

    public static void RSA_encrypt(BigInteger text) {
        BigInteger[] pq = getRandomPQ();
        generateKey(pq[0], pq[1]);
        /*test*/
        System.out.println("e:"+k.e.toString(16));
        System.out.println("d:"+k.d.toString(16));
        System.out.println("n:"+k.n.toString(16));
        /*test*/
        System.out.println(text.toString(10));
        BigInteger ciphertext=encrypt(text,k.getPublicKey());
        System.out.println(ciphertext.toString(10));
        //验证结果是否准确
        System.out.println(decrypt(ciphertext,k.getPrivateKey()).toString(10));
    }
    //解密
    public static void RSA_decrypt(BigInteger ciphertext) {
        System.out.println(decrypt(ciphertext,k.getPrivateKey()).toString(10));
    }
    public static void main(String[] args) {
       RSA_encrypt(new BigInteger("12143286148264812361846183648618461"));

    }
}

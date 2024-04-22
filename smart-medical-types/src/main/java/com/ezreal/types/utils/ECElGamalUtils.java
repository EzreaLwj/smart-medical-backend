package com.ezreal.types.utils;

import cn.hutool.core.codec.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;

/**
 * @author Ezreal
 * @Date 2024/4/16
 */
public class ECElGamalUtils {

    private static PublicKey elGamalPublicKey;

    private static PrivateKey elGamalPrivateKey;

    private static final String keySalt = "123";

    private static Cipher cipher;


    /**
     * 初始化公钥、私钥和加密对象
     */
    private static void init() {
        try {
            if (elGamalPrivateKey == null || elGamalPublicKey == null || cipher == null) {
                // 公钥加密,私钥解密
                Security.addProvider(new BouncyCastleProvider());

                // 初始化密钥
                AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("ElGamal");
                algorithmParameterGenerator.init(256);
                AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();

                DHParameterSpec dhParameterSpec = (DHParameterSpec) algorithmParameters.getParameterSpec(DHParameterSpec.class);
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ElGamal");
                keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                elGamalPublicKey = keyPair.getPublic();
                elGamalPrivateKey = keyPair.getPrivate();

                // 创建Cipher对象，用于加密和解密
                cipher = Cipher.getInstance("ElGamal");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ECElGamal加密
     *
     * @param content 加密的内容
     * @return 返回Base64处理加密字符串
     */
    public static String encryptECElGamal(String content) {
        try {
            init();
            // 加密数据
            cipher.init(Cipher.ENCRYPT_MODE, elGamalPublicKey);
            byte[] encryptedMessage = cipher.doFinal(content.getBytes());
            return Base64.encode(encryptedMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ECElGamal解密
     *
     * @param encryptedMessage 解密二进制字节流
     * @return 明文
     */
    public static String decryptECElGamal(byte[] encryptedMessage) {
        try {
            init();
            // 解密数据
            cipher.init(Cipher.DECRYPT_MODE, elGamalPrivateKey);
            byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
            return new String(decryptedMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试代码
     *
     * @param src
     */
    public static void ECElGamalTest(String src) {
        try {
            // 公钥加密,私钥解密
            Security.addProvider(new BouncyCastleProvider());

            // 1.初始化密钥
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance("ElGamal");
            algorithmParameterGenerator.init(256);
            AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();

            DHParameterSpec dhParameterSpec = (DHParameterSpec) algorithmParameters.getParameterSpec(DHParameterSpec.class);
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ElGamal");
            keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            elGamalPublicKey = keyPair.getPublic();
            elGamalPrivateKey = keyPair.getPrivate();

            // 2. 创建Cipher对象，用于加密和解密
            Cipher cipher = Cipher.getInstance("ElGamal");

            // 3. 加密数据
            cipher.init(Cipher.ENCRYPT_MODE, elGamalPublicKey);
            String originalMessage = "lwj";
            byte[] encryptedMessage = cipher.doFinal(originalMessage.getBytes());
            System.out.println("Encrypted Message:" + Base64.encode(encryptedMessage));

            // 4. 解密数据
            cipher.init(Cipher.DECRYPT_MODE, elGamalPrivateKey);
            byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
            System.out.println("Decrypted Message:" + new String(decryptedMessage));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

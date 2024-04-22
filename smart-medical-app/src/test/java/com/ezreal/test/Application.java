package com.ezreal.test;

import cn.hutool.core.codec.Base64;
import com.ezreal.types.utils.ECElGamalUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Ezreal
 * @Date 2024/4/22
 */
@Slf4j
@SpringBootTest
public class Application {

    @Test
    public void ecelgamalUtilTest() {
        String msg = "96";
        String encrypted = ECElGamalUtils.encryptECElGamal(msg);
        log.info("第一次加密");
        log.info("加密后的内容为：{}", encrypted);
        String decrypted = ECElGamalUtils.decryptECElGamal(Base64.decode(encrypted));
        log.info("解密后的内容为：{}", decrypted);

        encrypted = ECElGamalUtils.encryptECElGamal(msg);
        log.info("第二次加密");
        log.info("加密后的内容为：{}", encrypted);
        decrypted = ECElGamalUtils.decryptECElGamal(Base64.decode(encrypted));
        log.info("解密后的内容为：{}", decrypted);
    }

    @Test
    public void ecelgamalUtilTest100() {
        String msg = "100";
        String encrypted = ECElGamalUtils.encryptECElGamal(msg);
        log.info("加密后的内容为：{}", encrypted);
        String decrypted = ECElGamalUtils.decryptECElGamal(Base64.decode(encrypted));
        log.info("解密后的内容为：{}", decrypted);
    }
}

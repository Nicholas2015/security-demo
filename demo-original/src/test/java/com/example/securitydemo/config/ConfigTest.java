package com.example.securitydemo.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;

public class ConfigTest {

    @Test
    public void testGenerator(){
        StringKeyGenerator stringKeyGenerator = KeyGenerators.string();
        String key = stringKeyGenerator.generateKey();
        System.out.println(key);
        BytesKeyGenerator bytesKeyGenerator = KeyGenerators.secureRandom();
        byte[] bytes = bytesKeyGenerator.generateKey();
        System.out.println(bytes.toString());
        int keyLength = bytesKeyGenerator.getKeyLength();
        System.out.println(keyLength);
        BytesKeyGenerator shared = KeyGenerators.shared(15);
        String password = "secret";
        String valueToEncrypt = "HELLO";
        BytesEncryptor e = Encryptors.standard(password, key);
        byte[] encrypt = e.encrypt(valueToEncrypt.getBytes());
        byte[] decrypt = e.decrypt(encrypt);

    }
}

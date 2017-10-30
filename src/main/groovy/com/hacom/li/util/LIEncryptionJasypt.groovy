package com.hacom.li.util

import groovy.util.logging.Slf4j

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor

import org.bouncycastle.jce.provider.BouncyCastleProvider

/**
 * Created by Edgar Rios on 19/10/2017.
 * https://groovygrailsblogs.wordpress.com/tag/encryption/
 * https://gist.github.com/lyhcode/3637109
 */
@Slf4j
class LIEncryptionJasypt extends LIEncryption3DES{

    static StandardPBEStringEncryptor mySecondEncryptor

    @Override
    static String encrypt(String data) {
        generateEncryptor()
        mySecondEncryptor.encrypt(data);
    }

    static void generateEncryptor() {
        if (mySecondEncryptor == null) {
            mySecondEncryptor = new StandardPBEStringEncryptor();
            mySecondEncryptor.setProvider(new BouncyCastleProvider());
            mySecondEncryptor.setAlgorithm("PBEWITHSHA256AND128BITAES-CBC-BC");
            def keyValue = keyEncrypt.reverse()
            mySecondEncryptor.setPassword(keyValue);
        }
    }

    @Override
    static String decrypt(String encrytedData) {
        generateEncryptor()
        mySecondEncryptor.decrypt(encrytedData);
    }
}

package com.hacom.li.util

import groovy.util.logging.Slf4j
import org.apache.commons.codec.binary.Hex

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.security.Key

/**
 * Created by Edgar Rios on 19/10/2017.
 * https://groovygrailsblogs.wordpress.com/tag/encryption/
 * https://gist.github.com/lyhcode/3637109
 */
@Slf4j
class LIEncryption3DES {

    static String keyEncrypt = "" //"prueba12prueba11prueba10"

    static String encrypt(String data) {
        Key key = generateKey()
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding")
        c.init(Cipher.ENCRYPT_MODE, key, iv)
        byte[] encVal = c.doFinal(data.getBytes("UTF-8"))
        return new String(Hex.encodeHex(encVal))
    }

    static Key generateKey() {
        def keyValue = keyEncrypt.reverse().getBytes()
        return new SecretKeySpec(keyValue,"DESede")
    }

    static String decrypt(String encrytedData) {
        Key key = generateKey()
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding")
        c.init(Cipher.DECRYPT_MODE, key, iv)
        byte[] decVal = c.doFinal(Hex.decodeHex(encrytedData.toCharArray()))
        return new String(decVal)
    }
}

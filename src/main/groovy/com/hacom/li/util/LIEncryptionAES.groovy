package com.hacom.li.util

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Created by Edgar Rios on 27/10/2017.
 * https://gist.github.com/SimoneStefani/99052e8ce0550eb7725ca8681e4225c5
 */
class LIEncryptionAES {

    private static final String ALGO = "AES"; //Algoritmo
    static String keyEncrypt = "" //"prueba12prueba11prueba10"

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    public static String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encVal);
    }

    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }

    /**
     * Generate a new encryption key.
     */
    static Key generateKey() {
        def keyValue = keyEncrypt.reverse().getBytes()
        return new SecretKeySpec(keyValue,ALGO)
    }
}

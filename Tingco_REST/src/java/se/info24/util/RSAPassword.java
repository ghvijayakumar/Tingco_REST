/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.log4j.Logger;

/**
 *
 * @author Sridhar Dasari
 */
public class RSAPassword {

    private static final String PUBLIC_KEY_RSA_512_B64_X509 = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKusY6yP5LJxayo90UHwdhNVk7CI3Aktqs9+Dq90N6UphG1hhYCwjnQL/S6hhUjydh4KWZ8msmXxzKRDpo+VB4sCAwEAAQ==";

    @SuppressWarnings({"warning", "deprecation"})
    public static String encryptedPwd(String pwd) {
        Logger logger = Logger.getLogger(RSAPassword.class);
        BASE64Decoder b64dec = new BASE64Decoder();
        BASE64Encoder b64enc = new BASE64Encoder();
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(b64dec.decodeBuffer(PUBLIC_KEY_RSA_512_B64_X509));
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            SecureRandom sr = new SecureRandom();
            sr.setSeed(100);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, sr);
            byte[] cipherData = cipher.doFinal(pwd.getBytes(), 0, pwd.getBytes().length);
            String sEncryptedB64 = b64enc.encode(cipherData);
            return sEncryptedB64;
        } catch (Exception ex) {
            StringWriter stack = new StringWriter();
            ex.printStackTrace(new PrintWriter(stack));
            logger.error(stack.toString());
            return "";
        }
    }
}

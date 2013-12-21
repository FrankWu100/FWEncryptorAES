package FW.Encryptor;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import android.util.Base64; 
 
public class AES {
    public static void main(String[] args) throws Exception{
        /*
        //Sample Code
        String msg = "this is msg";
        String keyStr = "this is key";
        String ivStr = "this is iv";
         
        byte[] msg_byte = msg.getBytes("UTF-8");
        System.out.println("Before Encrypt: " + msg);
        
        byte[] ans = AES.encrypt(ivStr, keyStr, msg.getBytes());
        System.out.println("After Encrypt: " + new String(ans, "UTF-8"));
        
        byte[] deans = AES.decrypt(ivStr, keyStr, ans);
        System.out.println("After Decrypt: " + new String(deans, "UTF-8"));
		
        String ansBase64 = AES.encryptStrAndToBase64(ivStr, keyStr, msg);
        System.out.println("After Encrypt & To Base64: " + ansBase64);
		        
        String deansBase64 = AES.decryptStrAndFromBase64(ivStr, keyStr, ansBase64);
        System.out.println("After Decrypt & From Base64: " + deansBase64);
        */
    }
    
    public static byte[] encrypt(String ivStr, String keyStr, byte[] bytes) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(ivStr.getBytes());
        byte[] ivBytes = md.digest();
        
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(keyStr.getBytes());
        byte[] keyBytes = sha.digest();
        
        return encrypt(ivBytes, keyBytes, bytes);
    }
    
    static byte[] encrypt(byte[] ivBytes, byte[] keyBytes, byte[] bytes) throws Exception{
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
        return cipher.doFinal(bytes);
    }
        
    public static byte[] decrypt(String ivStr, String keyStr, byte[] bytes) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(ivStr.getBytes());
        byte[] ivBytes = md.digest();
        
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(keyStr.getBytes());
        byte[] keyBytes = sha.digest();
        
        return decrypt(ivBytes, keyBytes, bytes);
    }
    
    static byte[] decrypt(byte[] ivBytes, byte[] keyBytes, byte[] bytes)  throws Exception{
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return cipher.doFinal(bytes);
    }
    
    public static String encryptStrAndToBase64(String ivStr, String keyStr, String enStr) throws Exception{
        byte[] bytes = encrypt(keyStr, keyStr, enStr.getBytes("UTF-8"));
        return new String(Base64.encode(bytes ,Base64.DEFAULT), "UTF-8");
    }
    
    public static String decryptStrAndFromBase64(String ivStr, String keyStr, String deStr) throws Exception{
        byte[] bytes = decrypt(keyStr, keyStr, Base64.decode(deStr.getBytes("UTF-8"),Base64.DEFAULT));
        return new String(bytes, "UTF-8");
    }
}
import FW.Encryptor.AES;

class Sample {
	public static void main(String[] args) throws Exception{
        String msg = "this is msg";
        String keyStr = "this is key";
        String ivStr = "this is iv";
        
        byte[] msg_byte = msg.getBytes("UTF-8");
        System.out.println("Before Encrypt: " + msg);
        
        byte[] ans = AES.encrypt(ivStr, keyStr, msg.getBytes());
        System.out.println("After Encrypt: " + new String(ans, "UTF-8"));
        
        byte[] deans = AES.decrypt(ivStr, keyStr, ans);
        System.out.println("After Decrypt: " + new String(deans, "UTF-8"));

        String ansBase64 = AES.encryptStrToBase64(ivStr, keyStr, msg);
        System.out.println("After Encrypt & To Base64: " + ansBase64);
                
        String deansBase64 = AES.decryptStrFromBase64(ivStr, keyStr, ansBase64);
        System.out.println("After Decrypt & From Base64: " + deansBase64);
    }
}
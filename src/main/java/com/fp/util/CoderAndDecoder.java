package com.fp.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;


public class CoderAndDecoder {
	private static Logger logger = LoggerFactory.getLogger(CoderAndDecoder.class);
	
	  /**
     * AES加密字符串
     * 
     * @param content
     *            需要被加密的字符串
     * @param password
     *            加密需要的密码
     * @return 密文
     */
    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
                                                                    // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
                                                            // null。

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return result;

        } catch (NoSuchPaddingException e) {
        	logger.debug("{}",e);
        } catch (NoSuchAlgorithmException e) {
        	logger.debug("{}",e);
        } catch (UnsupportedEncodingException e) {
        	logger.debug("{}",e);
        } catch (InvalidKeyException e) {
        	logger.debug("{}",e);
        } catch (IllegalBlockSizeException e) {
        	logger.debug("{}",e);
        } catch (BadPaddingException e) {
        	logger.debug("{}",e);
        }
        return null;
    }
    
    /**
     * 解密AES加密过的字符串
     * 
     * @param content
     *            AES加密过过的内容
     * @param password
     *            加密时的密码
     * @return 明文
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] result = cipher.doFinal(content);  
            return result; // 明文   
            
        } catch (NoSuchAlgorithmException e) {
        	logger.debug("{}",e);
        } catch (NoSuchPaddingException e) {
        	logger.debug("{}",e);
        } catch (InvalidKeyException e) {
        	logger.debug("{}",e);
        } catch (IllegalBlockSizeException e) {
        	logger.debug("{}",e);
        } catch (BadPaddingException e) {
        	logger.debug("{}",e);
        }
        return null;
    }
    
    public static String getMD5(String str){
    	String slat="abcd";
		String base=str+"/"+slat;
		String md5=DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}
    
    
    
    
    public static void main(String[] args) throws UnsupportedEncodingException {
		/*String str ="1qaz2wsx";
		byte[] bytearr = encrypt(str,"fp");
		System.out.println(new String(bytearr,"utf8"));
		byte[] bytearr2 = decrypt(bytearr,"fp");
		System.out.println(new String(bytearr2,"utf8"));*/
    	String rtstr = getMD5("asdf");
    	System.out.println(rtstr);
    	/*byte [] byarr = DeMd5(rtstr.getBytes());
    	System.out.println(new String(byarr,"gbk"));*/
    	
    	
    	
		}
}

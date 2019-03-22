package com.zyh.spring.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.io.IOUtils;

/**
 * DES 加解密工具类
 * @author HU
 * @date 2019-02-20
 */
public class DesCryptUtil {
    /**
     * DES加密
     * @param datasource
     * @return
     */
    @SuppressWarnings("deprecation")
	public String encode(String datasource,String KEY,String CODE_TYPE){
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes(CODE_TYPE));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            byte[] temp = Base64.encode(cipher.doFinal(datasource.getBytes()));
            return IOUtils.toString(temp,"UTF-8");
        }catch(Throwable e){
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * DES解密
     * @return
     */
    @SuppressWarnings("deprecation")
	public String decode(String src,String KEY,String CODE_TYPE) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(KEY.getBytes(CODE_TYPE));
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return IOUtils.toString(cipher.doFinal(Base64.decode(src)),"UTF-8");
    }
    
}

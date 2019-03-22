package com.zyh.spring.utils;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zyh.spring.controller.sys.entity.SysUser;



/**
 * Token 加解密
 * 工具类
 * @author HU
 */
@Component
public class AccessTokenEncryptUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(AccessTokenEncryptUtil.class);
	
	// 秘钥
	@Value("${ACCESSTOKEN.SECRETKEY:}")
	private String secretkey;
	
	// 一周
	@Value("${ACCESSTOKEN.VALIDTIME:}")
	private String validtime;
		
	// 掩码
	@Value("${ACCESSTOKEN.MASK}")
	private String mask;
	
	public static String encryption(String secretData, String secretKey) throws Exception{
	    return   NumberUtils.bytesToHexString(DesUtil.encryptDES(secretData.getBytes(),secretKey));
	}
	
	public static String decryption(String secretData, String secretKey) throws Exception { 
		byte[] secretDataBytes= NumberUtils.hexStringToBytes(secretData);
		return new String(DesUtil.decryptDES(secretDataBytes, secretKey));
	}

	/**
	 * 生成加密token
	 * @param user
	 * @return
	 */
	public String getValidAccessToken(SysUser user) {
		Date currentDate = new Date();
		Date accessTokenDate = null;
		accessTokenDate = TimeUtils.addDays(currentDate, Integer.valueOf(validtime));
		String accessTokenDateVal = DateUtil.dateToString(accessTokenDate);
		String accessTokenRaw = user.getLoginName() + "$" + accessTokenDateVal + "$" + mask + "$1";
		String accessTokenValue = null;
		try {
			accessTokenValue = AccessTokenEncryptUtil.encryption(accessTokenRaw, secretkey);
			logger.info("---加密token为:" + accessTokenValue);
		} catch (Exception e) {
			logger.error("---加密token失败-1", e.getMessage());
		} catch (Throwable t) {
			logger.error("---加密token失败-2", t.getMessage());
		}
		return accessTokenValue;
	}
	
	public static void main(String[] a) throws Exception {
		String key = "464c078a83a1b046";
		String enStr = encryption("qwer123", key);
//		String desStr = new String(DesUtil.decryptDES("xPbdPalRy+4qCl1pylyLfA==".getBytes(), key));
		String desStr = decryption(enStr, key);
//		String desStr = new String(DesUtil.decryptDESEx("U2FsdGVkX1+NiiiKgh/kVAlNY2QGnWRiIBcWZ2BTSUA=".getBytes(), key));
		
		System.out.println("---解密之后数据为:" + desStr);
	}

}
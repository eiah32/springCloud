package com.eiah.config;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eiah.domain.User;
/**
 * 加密
 * created by eiah on 2017-07-02
 */
@Component
public class EncryptPassword {
	@Value("${password.algorithmName:md5}")  
    private String algorithmName;  
    @Value("${password.hashIterations:2}")  
    private  int hashIterations;
    
    /**
	 * 加密密码及生成盐
	 * @param user
	 * created by eiah on 2017-07-02
	 */
	public void encryptPassword(User user) {
		RandomNumberGenerator randomNumberGenerator  = new SecureRandomNumberGenerator();
	    user.setSalt(randomNumberGenerator.nextBytes().toHex());
	    updatePassword(user);
	}
	
	/**
	 * 加密密码
	 * @param user
	 * created by eiah on 2017-08-19
	 */
	public void updatePassword(User user) {
		String newPassword = new SimpleHash(
				algorithmName,           //加密算法  
                user.getPassword(),      //密码  
                ByteSource.Util.bytes(user.getCredentialsSalt()),  //salt盐   username + salt  
                hashIterations   //迭代次数  
				).toHex();
		user.setPassword(newPassword);
	}
}
 
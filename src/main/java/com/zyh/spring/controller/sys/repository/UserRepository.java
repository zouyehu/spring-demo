package com.zyh.spring.controller.sys.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zyh.spring.controller.sys.entity.SysUser;


public interface UserRepository extends BaseRepository<SysUser, Long>{

	/**
	 * 根据用户名得到 user对象
	 * @param loginName
	 * @return
	 */
	public SysUser findByLoginName(String loginName);
	
	/**
	 * 根据用户名更新用户密码
	 * @param loginName
	 * @param loginPassword
	 * @param modifyPasswordTime
	 */
	@Transactional
	@Modifying
	@Query(value = "update SYS_USER_INFO s set s.LOGIN_PASSWORD=:loginPassword,set s.MODIFY_PASSWORD_TIME=:modifyPasswordTime where s.LOGIN_NAME=:loginName",nativeQuery = true)
	public void updateLoginPassword(@Param("loginName") String loginName, @Param("loginPassword") String loginPassword,
			@Param("modifyPasswordTime") Date modifyPasswordTime);
	
}

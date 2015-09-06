package com.tuziilm.searcher.service;

import com.tuziilm.searcher.domain.SysUser;
import com.tuziilm.searcher.persistence.SysUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户数据操作服务类
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class SysUserService  extends SimpleCacheSupportService<SysUser> {
	private SysUserMapper sysUserMapper;
	@Autowired
	public void setSysUserMapper(SysUserMapper sysUserMapper) {
		this.mapper = sysUserMapper;
		this.sysUserMapper=sysUserMapper;
	}
	public SysUser getByEmail(String email) {
		return sysUserMapper.getByEmail(email);
	}
	public SysUser getByUsername(String username) {
		return sysUserMapper.getByUsername(username);
	}
}

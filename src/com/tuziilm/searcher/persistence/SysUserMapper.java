package com.tuziilm.searcher.persistence;

import com.tuziilm.searcher.domain.SysUser;

/**
 * ibatis操作系统用户表的Mapper接口
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser>{
	SysUser getByEmail(String email);
	SysUser getByUsername(String username);
}
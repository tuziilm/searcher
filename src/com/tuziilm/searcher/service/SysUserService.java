package com.tuziilm.searcher.service;

import com.tuziilm.searcher.domain.SysUser;
import com.tuziilm.searcher.persistence.SysUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户数据操作服务类
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class SysUserService  extends ObjectBasedGroupCacheSupportService<SysUser> {
	private final static String LIST_ALL_KEY="list_all_key";
	private final static String MAP_ALL_KEY="map_all_key";
	private SysUserMapper sysUserMapper;
	@Autowired
	public void setSysUserMapper(SysUserMapper sysUserMapper) {
		this.mapper = sysUserMapper;
		this.sysUserMapper=sysUserMapper;
	}
	@Override
	public String[] cacheGroupKeys() {
		return new String[]{LIST_ALL_KEY,MAP_ALL_KEY};
	}

	@Override
	public Object newObject(String cacheGroupKey) {
		if(cacheGroupKey.startsWith("map")){
			return new HashMap<String, SysUser>();
		}else{
			return new ArrayList<SysUser>();
		}
	}

	@Override
	public void updateCacheList(Map<String, Object> update, SysUser sysUser) {
		((List<SysUser>)update.get(LIST_ALL_KEY)).add(sysUser);
		((Map<Integer, SysUser>)update.get(MAP_ALL_KEY)).put(sysUser.getId(), sysUser);
	}
	public List<SysUser> getAllSysUsersCache(){
		return (List<SysUser>)getCache(LIST_ALL_KEY);
	}

	public Map<Integer, SysUser> getAllSysUsersMapCache(){
		return (Map<Integer, SysUser>)getCache(MAP_ALL_KEY);
	}

	public SysUser getByEmail(String email) {
		return sysUserMapper.getByEmail(email);
	}
	public SysUser getByUsername(String username) {
		return sysUserMapper.getByUsername(username);
	}
}

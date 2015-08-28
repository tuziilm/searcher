package com.tuziilm.searcher.persistence;

import com.tuziilm.searcher.domain.Id;
import com.tuziilm.searcher.common.Paginator;

import java.util.List;

/**
 * ibatis操作表的Mapper基础接口
 */
public interface BaseMapper<T extends Id> {
	
    int deleteById(Integer id);
    
    int deleteByIds(int ids[]);

    int insert(T channel);

    List<T> selectAll();
    
    List<T> select(Paginator page);

    int count(Paginator page);

    T selectById(Integer id);
    
    int updateByIdSelective(T t);
    
    int updateById(T t);
}
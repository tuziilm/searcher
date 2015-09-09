package com.tuziilm.searcher.persistence;


import com.tuziilm.searcher.domain.AppPack;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-1
 */
public interface AppPackMapper extends BaseMapper<AppPack>{
    AppPack getType4ByUid(Integer uid);
}

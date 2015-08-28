package com.tuziilm.searcher.service;

import com.tuziilm.searcher.domain.AppPack;
import com.tuziilm.searcher.persistence.AppPackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-2
 * Time: обнГ3:53
 */
@Service
public class AppPackService extends BaseService<AppPack> {

    private AppPackMapper appPackMapper;

    @Autowired
    public void setAppMapper(AppPackMapper appPackMapper) {
        this.mapper = appPackMapper;
        this.appPackMapper = appPackMapper;
    }
}

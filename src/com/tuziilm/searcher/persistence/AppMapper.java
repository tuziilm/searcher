package com.tuziilm.searcher.persistence;

import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.domain.App;

import java.util.List;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-1
 */
public interface AppMapper extends BaseMapper<App>{
    int countAll();
    int insertBatch(List<App> apps);
    App getAppByUniqueKey(Paginator paginator);
}

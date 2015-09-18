package com.tuziilm.searcher.persistence;

import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.domain.App;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-6-1
 */
public interface AppMapper extends BaseMapper<App>{
    int countAll();

    App getAppByUniqueKey(Paginator paginator);
}

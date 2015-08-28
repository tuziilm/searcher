package com.tuziilm.searcher.statistics.analyzer;

import com.tuziilm.searcher.statistics.common.ChartPvUvData;

import java.util.List;

public interface Analyzer {
    List<ChartPvUvData> analyze() throws Exception;
}

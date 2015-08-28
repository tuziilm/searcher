package com.tuziilm.searcher.statistics;

import com.tuziilm.searcher.statistics.analyzer.Analyzer;
import com.tuziilm.searcher.statistics.analyzer.LinkNodePvUvAnalyzer;
import com.tuziilm.searcher.statistics.common.ChartPvUvData;
import com.tuziilm.searcher.statistics.common.DatabaseHelper;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public final class LogStatisticsRunner {
	public final static void persistToDatabase(Analyzer analyzer) throws Exception {
		try {
			System.out.println("analyzing....");
			List<ChartPvUvData> data = analyzer.analyze();
			System.out.println("ready for persist result to database");
			DatabaseHelper.persistToDatabase(data);
		} catch (SQLException e) {
			System.out.println("failed to persist result to database");
			e.printStackTrace();
		}
		System.out.println("work done.");
	}
	public static void main(String[] args) throws Exception {
        Date date = new Date();
        if(args.length>0){
            date = DateUtils.parseDate(args[0], "yyyy-MM-dd");
        }
		LinkNodePvUvAnalyzer analyzer = new LinkNodePvUvAnalyzer(date);
		persistToDatabase(analyzer);
	}
}

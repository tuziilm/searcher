package com.tuziilm.searcher.statistics;

import com.tuziilm.searcher.common.Paginator;
import com.tuziilm.searcher.domain.App;
import com.tuziilm.searcher.mvc.AppController.AppUniqueKeyQuery;
import com.tuziilm.searcher.service.AppService;
import com.tuziilm.searcher.statistics.common.ExcelReader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: <a href="tuziilm@163.com">Tuziilm</a>
 * Date: 15-9-22
 * Time: 下午3:11
 */
public class TestReaderExcek {
    public static void main(String[] args) {
        try {
//            GenericXmlApplicationContext context = new GenericXmlApplicationContext();
//            context.setValidating(false);
//            context.load("classpath:../WebContent/WEB-INF/applicationContext.xml");
//            context.refresh();
//            AppService appService = context.getBean(AppService.class);
            String[] path={"WebContent/WEB-INF/applicationContext.xml",
                    "WebContent/WEB-INF/springMVC-servlet.xml"};
            ApplicationContext context = new FileSystemXmlApplicationContext(path);
            AppService appService = context.getBean(AppService.class);
            InputStream is = new FileInputStream("D://country1.xls");
            ExcelReader excelReader = new ExcelReader();

            //下面读取字段内容
            POIFSFileSystem fs;
            HSSFWorkbook wb;
            HSSFSheet sheet;
            HSSFRow row;

            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            sheet = wb.getSheetAt(0);

            //得到总行数
            int rowNum = sheet.getLastRowNum();
            String temp;
//            for (int i = 1; i <= rowNum;i++ ) {
            row = sheet.getRow(1);
            int j = 2;
            temp = excelReader.getStringCellValue(row.getCell((short) j));
            String[] apps = temp.split("\n");
            List<App> appList = new ArrayList<>();
            for (String str : apps) {
                App app = new App();
                app.setUid(1);
                String[] names = str.split("\\.");
                if (names.length <= 0) {
                    continue;
                }
                app.setName(names[0]);
                app.setLink("http://" + str);
                app.setType(3);
                app.setStatus(0);
                Paginator paginator = new Paginator();
                AppUniqueKeyQuery unique = new AppUniqueKeyQuery();
                unique.setLink(app.getLink());
                unique.setName(app.getName());
                unique.setType(3);
                paginator.setQuery(unique);
                App app1 = appService.getAppByUniqueKey(paginator);
                if(app1==null){
                    appList.add(app);
                }
            }

            appService.insertAllApps(appList);
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

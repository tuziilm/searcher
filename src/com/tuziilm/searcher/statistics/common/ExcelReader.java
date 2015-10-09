package com.tuziilm.searcher.statistics.common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

    /**
     * Author: <a href="tuziilm@163.com">Tuziilm</a>
     * Date: 15-9-22
     * Time: ����2:18
     * ����Excel���Ĺ�����
     * @author��
     * @version
     */
    public class ExcelReader {
        private POIFSFileSystem fs;
        private HSSFWorkbook wb;
        private HSSFSheet sheet;
        private HSSFRow row;
        /**
         * ��ȡExcel����ͷ������
         * @return String ��ͷ���ݵ�����
         *
         */
        public String[] readExcelTitle(InputStream is) {
            try {
                fs = new POIFSFileSystem(is);
                wb = new HSSFWorkbook(fs);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sheet = wb.getSheetAt(0);
            row = sheet.getRow(0);
            //����������
            int colNum = row.getPhysicalNumberOfCells();
            String[] title = new String[colNum];
            for (int i=0; i<colNum; i++) {
                title[i] = getTitleValue(row.getCell((short) i));
            }
            return title;
        }


        /**
         * ��ȡ��Ԫ����������Ϊ�ַ������͵�����
         * @param cell Excel��Ԫ��
         * @return String ��Ԫ���������ݣ���Ϊ�ַ�����Ҫ�ӵ�����
         */
        public String getStringCellValue(HSSFCell cell) {
            String strCell = "";
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_STRING:
                    strCell = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    strCell = String.valueOf(cell.getNumericCellValue());
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    strCell = "''";
                    break;
                default:
                    strCell = "''";
                    break;
        }
        if (strCell.equals("''") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    public String getTitleValue(HSSFCell cell) {
        String strCell =  cell.getStringCellValue();
        return strCell;
    }
}

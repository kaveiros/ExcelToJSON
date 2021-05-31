package com.excel.reader.engine;
import com.excel.reader.ExcelToJson.ExcelRecord;
import com.excel.reader.ExcelToJson.RowRecord;
import com.excel.reader.model.ExcelToJson;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class ExcelReader {

    private final String ADDRESS = "address" ;
    private final String CONTACT = "SME Contact" ;
    private final String IGNORE = "IGNORE";
    private final String IGNORE_CLASSIFICATION = "IGNORE / Platforms will create a mapping for ACTOR_CLASSIFICATION";

    public List<ExcelToJson> readExcelFile(String path) {

        Sheet sheet = readExcel(path, null);
        List<ExcelRecord> excelTitlesAllRows = new ArrayList<>();
        Map<Integer, Object> record = new HashMap<>();
        List<ExcelToJson> jsonList = new ArrayList<>();


        List<RowRecord> rowRecordList = getRowRecords(sheet, 1);

        ExcelUtils utils = new ExcelUtils();
//       long freq =  utils.calculateArraySection(rowRecordList.get(0).getExcelRecordsPerRow(), "ADDRESS / FULL_ADDRESS");
//        List<ExcelRecord> address = utils.getEntries(rowRecordList.get(0).getExcelRecordsPerRow(), "ADDRESS / FULL_ADDRESS");
        return  utils.generateJSON(rowRecordList);

    }

    private List<RowRecord> getRowRecords(Sheet sheet, int rowsToOmmit) {
        List<RowRecord> rowRecordList = new ArrayList<>();

        /**
         *create object get values for excel and db, add index and row. This way will know how to split them per line
         */
        for (Row row : sheet) {
            List<ExcelRecord> cellsPerRow = new ArrayList<>();
            if (row.getRowNum() > rowsToOmmit) {

                RowRecord rowRecord = new RowRecord();
                rowRecord.setRowId(row.getRowNum());
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    String dbTitle="";
                    if(sheet.getRow(1).getCell(cell.getColumnIndex()).getCellType() == CellType.NUMERIC){
                        dbTitle = String.valueOf(sheet.getRow(1).getCell(cell.getColumnIndex()).getNumericCellValue());
                    }
                    if(sheet.getRow(1).getCell(cell.getColumnIndex()).getCellType() == CellType.STRING){
                        dbTitle = sheet.getRow(1).getCell(cell.getColumnIndex()).getStringCellValue();
                    }

                    System.out.println(dbTitle);
                    String cellTitle = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
                    String hubId1 = "";
                    if(sheet.getRow(row.getRowNum()).getCell(0) != null && sheet.getRow(row.getRowNum()).getCell(0).getCellType() == CellType.STRING) {
                        hubId1 += sheet.getRow(row.getRowNum()).getCell(0).getStringCellValue();
                    }
                    if(sheet.getRow(row.getRowNum()).getCell(0) != null &&  sheet.getRow(row.getRowNum()).getCell(0).getCellType() == CellType.NUMERIC) {
                        hubId1 += String.valueOf(sheet.getRow(row.getRowNum()).getCell(0).getNumericCellValue());
                    }

                    if (rowsToOmmit == 1) {

//                        if(row.getCell(0)!=null && row.getCell(0).getCellType()==CellType.NUMERIC){
//                            Double hubId = row.getCell(0).getNumericCellValue();
//                            rowRecord.setHubId(hubId.toString());
//                        }
//                        if(row.getCell(0)!=null && row.getCell(0).getCellType()==CellType.STRING){
//                            String hubId = row.getCell(0).getStringCellValue();
//                            rowRecord.setHubId(hubId.toString());
//                        }


                        if (!dbTitle.equals(IGNORE) && !dbTitle.equals(IGNORE_CLASSIFICATION)) {
                            cellsPerRow.add(getCellContent(cell, cellTitle, dbTitle, hubId1));

                        }

                    }
                    //used in case we need to map data
                    else {
                        System.out.println(hubId1);
                        cellsPerRow.add(getCellContent(cell, cellTitle, dbTitle, hubId1));
                    }

                }
                rowRecord.setExcelRecordsPerRow(cellsPerRow);
                rowRecordList.add(rowRecord);
            }

        }
        return rowRecordList;
    }


    private ExcelRecord getCellContent(Cell cell, String cellTitle, String dbTitle, String hubId) {
        ExcelRecord excelRecord = new ExcelRecord();
        /**
         *create object get values for excel and db, add index and row. This way will know how to split them per line
         */
        int index = cell.getColumnIndex();
        excelRecord.setExcel(cellTitle);
        excelRecord.setIndex(index);
        excelRecord.setDb(dbTitle);
        excelRecord.setHubId(hubId);
        if (cell.getCellType().equals(CellType.NUMERIC)) {
            excelRecord.setContent(String.valueOf(cell.getNumericCellValue()));

        }
        if (cell.getCellType().equals(CellType.STRING)) {
            if (cell.getStringCellValue() != null) {
                excelRecord.setContent(cell.getStringCellValue());
            }
        }
        return excelRecord;
    }


    public Sheet readExcel(String path, String sheetName){
        Sheet sheet = null;
        try(FileInputStream file = new FileInputStream(new File(path));) {
            Workbook wb = null;
            String excelPath = FilenameUtils.getExtension(path);
            if (excelPath.equals("xls")) {
                //XSSF
                System.out.println("xls");
                wb = new HSSFWorkbook(file);


            }
            if (excelPath.equals("xlsx")) {
                //HSSF
                System.out.println("xlsx");
                wb = new XSSFWorkbook(file);

            }

            //sheet = wb.getSheet("ATI Technology Centre");\
            if (sheetName == null) {
                sheet = wb.getSheetAt(0);
            }
            else{
                sheet = wb.getSheet(sheetName);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();

        }

        return sheet;

    }


    public List<RowRecord> readSheetToBeMapped(Sheet sheet) {
        List<ExcelRecord> records = new ArrayList<>();
        return  getRowRecords(sheet, 0);


    }

}

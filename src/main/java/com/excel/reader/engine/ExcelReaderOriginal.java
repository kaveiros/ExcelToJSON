package com.excel.reader.engine;
import com.excel.reader.ExcelToJson.ExcelRecord;
import com.excel.reader.ExcelToJson.RowRecord;
import com.excel.reader.model.ActorUrl;
import com.excel.reader.model.Address;
import com.excel.reader.model.Aggregation;
import com.excel.reader.model.ContactDetails;
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
import java.util.stream.Collectors;

@Component
public class ExcelReaderOriginal {

    private final String ADDRESS = "address" ;
    private final String CONTACT = "SME Contact" ;
    private final String IGNORE = "IGNORE";
    private final String IGNORE_CLASSIFICATION = "IGNORE / Platforms will create a mapping for ACTOR_CLASSIFICATION";

    public List<ExcelToJson> readExcelFile(String path) {

        List<ExcelRecord> excelRecords = new ArrayList<>();
        List<ExcelRecord> excelTitlesAllRows = new ArrayList<>();
        Map<Integer, Object> record = new HashMap<>();
        List<ExcelToJson> jsonList = new ArrayList<>();
        List<ExcelRecord> recordsPerRow = new ArrayList<>();
        List<RowRecord> rowRecordList = new ArrayList<>();

        //aggregation
        //linked_actor
        //contact_details
        //actor_identifier
        //actor_url
        //actor_collaboration_relationship
        try(FileInputStream file = new FileInputStream(new File(path));) {
            Workbook wb = null;
            String excelPath = FilenameUtils.getExtension(path);
            if (excelPath.equals("xls")) {
                //XSSF
                System.out.println("xls");
                wb = new HSSFWorkbook(file);


            }
            if (excelPath.equals("xlsx"))
            {
                //HSSF
                System.out.println("xlsx");
                wb = new XSSFWorkbook(file);

            }

            Sheet sheet = wb.getSheet("ATI Technology Centre");


            //Iterate through each rows one by one
            /**
             *create object get values for excel and db, add index and row. This way will know how to split them per line
             */
            for (Row row : sheet) {
                Aggregation aggregation = new Aggregation();
                List<Aggregation> aggregationList = new ArrayList<>();
                ContactDetails contactDetails = new ContactDetails();
                List<ContactDetails> contactDetailsList = new ArrayList<>();

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (!sheet.isColumnHidden(cell.getColumnIndex())) {
                        if (row.getRowNum() == 0) {
                            String db = sheet.getRow(1).getCell(cell.getColumnIndex()).getStringCellValue();
                            String cellTitle = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
                            if (!db.equals(IGNORE) && !db.equals(IGNORE_CLASSIFICATION)) {

                                ExcelRecord title = new ExcelRecord();
                                String excelValue = cell.getStringCellValue();
                                title.setExcel(cellTitle);
                                title.setIndex(cell.getColumnIndex());
                                title.setDb(db);
                                title.setIndex(cell.getColumnIndex());
                                title.setContent(cell.getStringCellValue());
                                record.put(cell.getColumnIndex(), title);
                                excelRecords.add(title);
                            }
                        }


                        if (row.getRowNum() > 1) {
                            RowRecord rowRecord = new RowRecord();

                            /**
                             *create object get values for excel and db, add index and row. This way will know how to split them per line
                             */
                            int index = cell.getColumnIndex();
                            ExcelRecord title = (ExcelRecord) record.get(index);

                            if (title != null) {
                                ExcelRecord rowExcelRecord = new ExcelRecord();
                                rowExcelRecord.setExcel(title.getExcel());
                                rowExcelRecord.setIndex(index);
                                rowExcelRecord.setDb(title.getDb());
                                rowExcelRecord.setRow(row.getRowNum());
                                if (cell.getCellType().equals(CellType.NUMERIC)) {
                                    rowExcelRecord.setContent(String.valueOf(cell.getNumericCellValue()));

                                }
                                if (cell.getCellType().equals(CellType.STRING)) {
                                    if (cell.getStringCellValue() != null) {
                                        rowExcelRecord.setContent(cell.getStringCellValue());
                                    }
                                }
                                //excelTitlesAllRows.add(rowExcelRecord);
                                recordsPerRow.add(rowExcelRecord);
                            }

                            rowRecord.setRowId(row.getRowNum());
                            rowRecord.setExcelRecordsPerRow(recordsPerRow);
                            rowRecordList.add(rowRecord);
                        }
                    }
                }

                System.out.println("Records per row");

            }


            /**
             * Create sub collections of records that belong to same row.
             */
            Map<Integer, List<ExcelRecord>> excelRecordsPerRow =
                    excelTitlesAllRows.stream().collect(Collectors.groupingBy(ExcelRecord::getRow));
            for (Map.Entry<Integer, List<ExcelRecord>> entry : excelRecordsPerRow.entrySet()) {
                List<ExcelRecord> row =  entry.getValue();
                jsonList.add(generateJSON(row));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return jsonList;

    }

    private ExcelToJson generateJSON(List<ExcelRecord> rowRecords){


        ExcelToJson json = new ExcelToJson();
        List<ActorUrl> actorUrlList = new ArrayList<>();
        Map<String, String> addressMap = new HashMap<>();
        //Check the cell type and format accordingly

        for(ExcelRecord record : rowRecords) {
            switch (record.getDb()) {
                case "ACTOR / ACTOR_NAME":
                    json.setActorName(record.getContent());
                    break;
                case "ACTOR / ACTOR_DESCRIPTION (Append under the main description)":
                    if(record.getExcel().equals("Services")){
                        json.setServices(record.getContent());
                    }
                    else if (record.getExcel().equals("Equipment")) {
                        json.setEquipment(record.getContent());
                    }
                    //aggregation.setPlatformActorAccessUrl(cell.getStringCellValue());
                    // aggregationList.add(aggregation);
                    // json.setAggregationList(aggregationList);
                    //System.out.print("AGGREGATION" + cell.getStringCellValue() + "  ");

                    break;
                case "AGGREGATION / PLATFORM_ACTOR_ACCESS_URL":
                    List<Aggregation> aggregationList = new ArrayList<>();
                    Aggregation aggregation = new Aggregation();
                    aggregation.setPlatformActorAccessUrl(record.getContent());
                    aggregationList.add(aggregation);
                    json.setAggregationList(aggregationList);
                    break;
                case "ACTOR / ACTOR_DESCRIPTION":
                    json.setActorDescription(record.getContent());
                    break;
                //case "Web-link for equipment":
                case "ACTOR_URL / ACTOR_URL":
                    ActorUrl actorUrl = new ActorUrl();
                    actorUrl.setActorUrl(record.getContent());
                    actorUrlList.add(actorUrl);
                    break;
                case "ADDRESS / FULL_ADDRESS":
                    if(record.getContent()!=null && !record.getContent().isEmpty()) {


                        addressMap.put("ADDRESS / FULL_ADDRESS" + record.getRow(),record.getContent());
                    }
                    break;
                case "ADDRESS / ADMIN_UNIT_L1":
                    if(record.getContent()!=null && !record.getContent().isEmpty()) {

                        addressMap.put("ADDRESS / ADMIN_UNIT_L1" + record.getRow(),record.getContent());
                    }
                    break;
                case "ADDRESS / ADMIN_UNIT_L2":
                    if(record.getContent()!=null && !record.getContent().isEmpty()) {

                        addressMap.put("ADDRESS / ADMIN_UNIT_L2" + record.getRow(),record.getContent());
                    }
                    break;
                case "ADDRESS / STATE":
                    addressMap.put("ADDRESS / STATE", record.getContent());
                    break;
//                case 12:
//                    break;
//                case 13:
//                    break;
//                case 14:
//                    //////json.setActorDescription(cell.getStringCellValue());
//                    break;
//                case 15:
//                    //json.setEquipment(cell.getStringCellValue());
//                    break;
//                case 16:
//                    break;
//                case 17:
//                    //json.setServices(cell.getStringCellValue());
//                    break;
//                case 18:
//                    break;
//                case 19:
//                    break;
//                case 20:
//                    break;
//                case 21:
//                    break;
//                case 22:
//                    break;
//                case 23:
//                    break;
//                case 24:
//                    break;
//                case 25:
//                    break;
//                case 26:
//                    break;
//                case 27:
//                    break;
//                case 28:
//                    break;
//                case 29:
//                    break;
//                case 30:
//                    break;
//                case 31:
//                    break;
//                case 32:
//                    break;
//                case 33:
//                    break;
//                case 34:
//                    break;
//                case 35:
//                    break;
//                case 36:
//                    break;

            }
            json.setActorUrls(actorUrlList);
        }
        List<Address> addressList = getAddressesForJson(addressMap);
       // json.setLocations(addressList);


        return json;
    }

    private List<Address> getAddressesForJson(Map<String, String> addressMap) {
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        for (Map.Entry<String, String> addressEntry: addressMap.entrySet()) {
            if(addressEntry.getKey().contains("ADDRESS / FULL_ADDRESS")){
                address.setAddress(addressEntry.getValue());
            }
            if (addressEntry.getKey().contains("ADDRESS / ADMIN_UNIT_L2")) {
                address.setAdminUnitIdL2(addressEntry.getValue());
            }
            if (addressEntry.getKey().contains("ADDRESS / ADMIN_UNIT_L1")) {
                address.setAdminUnitIdL1(addressEntry.getValue());
            }
            if (addressEntry.getKey().contains("ADDRESS / STATE")) {
                address.setState(addressEntry.getValue());
            }
        }
        addresses.add(address);
        return addresses;
    }

}

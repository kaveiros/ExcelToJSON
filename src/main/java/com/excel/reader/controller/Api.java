package com.excel.reader.controller;

import com.excel.reader.ExcelToJson.ExcelRecord;
import com.excel.reader.ExcelToJson.MappingRecord;
import com.excel.reader.ExcelToJson.RowRecord;
import com.excel.reader.engine.ExcelReader;
import com.excel.reader.engine.ExcelUtils;
import com.excel.reader.input.ExcelMergeRequest;
import com.excel.reader.input.ExcelRequest;
import com.excel.reader.model.Actor;
import com.excel.reader.model.ExcelToJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class Api {

    @Autowired
    private ExcelReader excelReader;

    @RequestMapping(path="/generate-json", method = RequestMethod.POST , produces = "application/json")
    public ResponseEntity<String> generateJson (@RequestBody ExcelRequest request) throws JsonProcessingException {
        System.out.println(request.getFilePath());
        List<ExcelToJson> jsonList = excelReader.readExcelFile(request.getFilePath());
        Actor actor = new Actor();
        //to be removed if id will be calculated somehow
        long generatedLong = new Random().nextLong();
        actor.setPlatformId(generatedLong);
        actor.setActors(jsonList);
        ObjectMapper mapper = new ObjectMapper();
        return ResponseEntity.ok(mapper.writeValueAsString(actor));
    }


    @RequestMapping(path = "/map", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> mapFields(@RequestBody ExcelMergeRequest request) {

        Sheet sheet = excelReader.readExcel(request.getExcelToMerge(), request.getSheetName());//read sheet from excel containing values.
        Sheet originalSheet = excelReader.readExcel(request.getOriginalExcel(), null);//read inital sheet
        List<RowRecord> originalRecords = excelReader.readSheetToBeMapped(originalSheet);//read records from initial records.
        List<MappingRecord> mappingRecordList = getMappingRecords(sheet);

        //get entries tha
        ExcelUtils excelUtils = new ExcelUtils();
        List<ExcelRecord> replacementRecords = new ArrayList<>();
        fillReplacementRecords(originalRecords, mappingRecordList, excelUtils, replacementRecords);

        return ResponseEntity.ok("Mapping completed");
    }

    private void fillReplacementRecords(List<RowRecord> originalRecords, List<MappingRecord> mappingRecordList, ExcelUtils excelUtils, List<ExcelRecord> replacementRecords) {
        for (MappingRecord mappingRecord : mappingRecordList) {
            String id = mappingRecord.getId();
            String mappingString = mappingRecord.getValue();

            for (RowRecord record : originalRecords) {
                List<ExcelRecord> p = excelUtils.getRowEntriesAccordingToId(record.getExcelRecordsPerRow(), id);
                if(!p.isEmpty()){
                    System.out.println("p size " + p.size());
                    List<ExcelRecord> re = excelUtils.getEntriesForMapping(p, "TECHNOLOGY / TECHNOLOGY_NAME");
                    for(ExcelRecord ex : re) {
                        if (ex.getExcel().contains(mappingString)) {
                            ex.setContent(mappingString);
                            replacementRecords.add(ex);
                        }
                    }
                    System.out.println("re size"+ re.size());

                }
            }
            System.out.println("");
        }
    }

    private List<MappingRecord> getMappingRecords(Sheet sheet) {
        List<RowRecord> recordsToBeMapped  = excelReader.readSheetToBeMapped(sheet);//read all records from sheet to be mapped.
        List<MappingRecord> mappingRecordList = new ArrayList<>();
        //add all records from sheet to be mapped to a list
        for(RowRecord rowRecord: recordsToBeMapped) {
            MappingRecord mappingRecord = new MappingRecord();
            if(rowRecord.getExcelRecordsPerRow().size() == 2 ){
                mappingRecord.setId(rowRecord.getExcelRecordsPerRow().get(1).getContent());
                mappingRecord.setValue(rowRecord.getExcelRecordsPerRow().get(0).getContent());
                mappingRecordList.add(mappingRecord);
            }
        }
        return mappingRecordList;
    }
}

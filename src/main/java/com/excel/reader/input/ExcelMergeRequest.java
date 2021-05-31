package com.excel.reader.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExcelMergeRequest {

    @JsonProperty("excel-to-merge")
    private String excelToMerge;

    @JsonProperty("sheet-name")
    private String sheetName;

    @JsonProperty("original-excel")
    private String originalExcel;

    public String getExcelToMerge() {
        return excelToMerge;
    }

    public void setExcelToMerge(String excelToMerge) {
        this.excelToMerge = excelToMerge;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getOriginalExcel() {
        return originalExcel;
    }

    public void setOriginalExcel(String originalExcel) {
        this.originalExcel = originalExcel;
    }
}

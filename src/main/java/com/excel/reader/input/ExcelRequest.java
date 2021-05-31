package com.excel.reader.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Class used to map excel parameters
 * request.
 * Used to define excel for conversion.
 */
public class ExcelRequest {

    @JsonProperty("file-path")
    private String filePath;

    @JsonProperty("sheet-name")
    private String sheetName;


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}

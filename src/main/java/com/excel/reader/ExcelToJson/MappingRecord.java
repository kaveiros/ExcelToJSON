package com.excel.reader.ExcelToJson;

/**
 * Class used for records that need
 * to be merged with original excel.
 */
public class MappingRecord {

    private String id;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

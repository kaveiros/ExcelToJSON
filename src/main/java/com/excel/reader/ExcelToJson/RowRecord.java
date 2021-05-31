package com.excel.reader.ExcelToJson;

import java.util.List;

/**
 * class used to map records
 * for same row.
 */
public class RowRecord {

    private int rowId;

    private String hubId;

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    private List<ExcelRecord> excelRecordsPerRow;

    public List<ExcelRecord> getExcelRecordsPerRow() {
        return excelRecordsPerRow;
    }

    public void setExcelRecordsPerRow(List<ExcelRecord> excelRecordsPerRow) {
        this.excelRecordsPerRow = excelRecordsPerRow;
    }

    public String getHubId() {
        return hubId;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
    }
}

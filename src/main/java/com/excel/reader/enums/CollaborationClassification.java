package com.excel.reader.enums;

public enum CollaborationClassification {

    ALLIANCE(4),

    CLUSTER(5),

    ENDEAVOUR(2),

    PROJECT(1),

    VENTURE(3);

    private final int label;

    CollaborationClassification(int label) {
        this.label = label;
    }

    public int getLabel() {
        return label;
    }
}

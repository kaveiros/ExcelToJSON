package com.excel.reader.enums;

public enum ActorType {

    DA("Direct Actor"),

    PA("Parent Organisation"),

    OT("Other");

    private final String label;

    ActorType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

package com.excel.reader.enums;

public enum ActorClassification {

    L1("Research, Applied Research Supporting Actors"),

    L1_1("Research and Technology Organisations"),

    L1_2("Technology centres"),

    L1_3("Pilot Production Facilities"),

    L1_4("Innovation Hubs"),

    L1_5("Technical Universities"),

    L2("Industry, Business Associations"),

    L2_1("Cluster Organisation"),

    L2_2("Alliance"),

    L2_3("Chamber of Commerce"),

    L3("Educational Actors"),

    L3_1("Vocational Training Institute"),

    L4("Investors"),

    L4_1("Investment Fund"),

    L4_2("Private Equity Firm"),

    L4_3("Venture Capitalist"),

    L4_4("Accelerator"),

    L4_5("Incubator"),

    L5("Public Authority"),

    L5_1("Innovation Agency"),

    L6("Other");

        private final String label;

    ActorClassification(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

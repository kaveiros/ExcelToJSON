package com.excel.reader.enums;

public enum TechnologyReadinessEnum {

    /*
    actor_classification
actor_type
technology_type
technology_readiness_level
sector_type
aggregator_platform
collaboration_classification
     */

    TRL1("Basic principles observed"),
    TRL2("Technology concept formulated"),
    TRL3("Experimental proof of concept"),
    TRL4("Technology validated in lab"),
    TRL5("Technology validated in relevant environment (industrially relevant environment in the case of key enabling technologies)"),

    TRL6("Technology demonstrated in relevant environment (industrially relevant environment in case of key enabling technologies)"),

    TRL7("System prototype demonstration in operational environment"),
    TRL8("System complete and qualified"),
    TRL9("Actual system proven in operational environment (competitive manufacturing in case of key enabling technologies; or in space)");




    private final String label;

    TechnologyReadinessEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

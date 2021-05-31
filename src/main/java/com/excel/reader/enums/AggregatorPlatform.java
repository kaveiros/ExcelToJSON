package com.excel.reader.enums;

import java.util.Arrays;
import java.util.List;

public enum AggregatorPlatform {

    DIH("Digital Innovation Hubs",	"The Digital Innovation Hubs tool is an online catalogue that contains comprehensive information on digital innovation hubs in Europe. The ultimate purpose of the tool is to help companies get access to competences needed in order to digitize their products and services. The catalogue gives European DIHs a platform to network with each other, other innovation support organisations and companies, in order to communicate their expertise.",	"https://s3platform.jrc.ec.europa.eu/digital-innovation-hubs-tool",	"Digital innovation hubs"),
    ATI("Advanced Technologies for Industry",	"The Advanced Technologies for Industry (ATI) website provides policymakers, industry representatives and academia with :* Statistical data on the creation and use of advanced technologies, * Analytical reports on technological trends, sectoral insights and products., * Analyses of policy measures and policy tools related to the uptake of advanced technologies., * Analyses of technological trends in competing economies such as in the US, China or Japan., * Access to technology centres and innovation hubs across EU countries.",
    "https://ati.ec.europa.eu/",	"Advanced technology centres"),
    KET4CP("KET4 Clean Production",	"This is the description of KET4CP",	"https://www.ket4sme.eu/",	"KET TC"),
    EEN("European Enterprise Network",	"The Enterprise Europe Network helps businesses innovate and grow on an international scale. It is the world’s largest support network for small and medium-sized enterprises (SMEs) with international ambitions. The Network is active in more than 60 countries worldwide. It brings together 3,000 experts from more than 600 member organisations – all renowned for their excellence in business support."+
    "Member organisations include: technology poles, innovation support organisations, universities and research institutes, regional development organisations, and chambers of commerce and industry",	"https://een.ec.europa.eu/",	"European Enterprise Network members");

    private final List<String> values;

    AggregatorPlatform(String ...values) {
        this.values = Arrays.asList(values);
    }

    public List<String> getValues() {
        return values;
    }

}

package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping actor section of json.
 */
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
public class Actor {

    @JsonProperty("platform_id")
    private long platformId;

    @JsonProperty("actors")
    private List<ExcelToJson> actors;

    public long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(long platformId) {
        this.platformId = platformId;
    }

    public List<ExcelToJson> getActors() {
        return actors;
    }

    public void setActors(List<ExcelToJson> actors) {
        this.actors = actors;
    }
}

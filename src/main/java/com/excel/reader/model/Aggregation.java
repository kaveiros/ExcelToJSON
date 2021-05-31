package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping aggregation section of JSON
 */
public class Aggregation {

    @JsonProperty("platform_actor_access__url")
    private String platformActorAccessUrl;

    public String getPlatformActorAccessUrl() {
        return platformActorAccessUrl;
    }

    public void setPlatformActorAccessUrl(String platformActorAccessUrl) {
        this.platformActorAccessUrl = platformActorAccessUrl;
    }
}

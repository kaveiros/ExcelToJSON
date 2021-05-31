package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping actor url element.
 */
public class ActorUrl {

    @JsonProperty("actor_url")
    private String actorUrl;

    public String getActorUrl() {
        return actorUrl;
    }

    public void setActorUrl(String actorUrl) {
        this.actorUrl = actorUrl;
    }
}

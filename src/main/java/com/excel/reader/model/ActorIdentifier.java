package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping actor identifier section
 * of JSON
 */
public class ActorIdentifier {

    @JsonProperty("actor_identifier")
    private String actorIdentifier;

    public String getActorIdentifier() {
        return actorIdentifier;
    }

    public void setActorIdentifier(String actorIdentifier) {
        this.actorIdentifier = actorIdentifier;
    }
}

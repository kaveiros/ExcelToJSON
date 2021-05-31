package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping actor_classification section of JSON
 */
public class ActorClassification {

    @JsonProperty("actor_classification_id")
    private int actorClassificationId;

    @JsonProperty("actor_classification_label")
    private String actorClassificationLabel;

    @JsonProperty("actor_classification_notation")
    private String actorClassificationNotation;

    @JsonProperty("actor_classification_parent")
    private String actorClassificationParent;

    public int getActorClassificationId() {
        return actorClassificationId;
    }

    public void setActorClassificationId(int actorClassificationId) {
        this.actorClassificationId = actorClassificationId;
    }

    public String getActorClassificationLabel() {
        return actorClassificationLabel;
    }

    public void setActorClassificationLabel(String actorClassificationLabel) {
        this.actorClassificationLabel = actorClassificationLabel;
    }

    public String getActorClassificationNotation() {
        return actorClassificationNotation;
    }

    public void setActorClassificationNotation(String actorClassificationNotation) {
        this.actorClassificationNotation = actorClassificationNotation;
    }

    public String getActorClassificationParent() {
        return actorClassificationParent;
    }

    public void setActorClassificationParent(String actorClassificationParent) {
        this.actorClassificationParent = actorClassificationParent;
    }
}

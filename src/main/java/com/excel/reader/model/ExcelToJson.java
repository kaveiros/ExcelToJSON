package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Class mapping excel file to JSON.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
//    @JsonPropertyOrder({"actor_classification__id",
//            "actor_type__id", "equipment", "services", "sme_industry_service", "service_provided",
//            "actor_name", "alternative_actor_name", "actor_description", "aggregation", "linked_actor",
//
//    })
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ExcelToJson {

    @JsonProperty("actor_classification__id")
    private long actorClassificationId;

    @JsonProperty("actor_type__id")
    private long actorTypeId;

    @JsonProperty("equipment")
    private String equipment;

    @JsonProperty("services")
    private String services;

    @JsonProperty("sme_industry_service")
    private String smeIndustryService;

    @JsonProperty("service_provided")
    private String serviceProvided;

    @JsonProperty("actor_name")
    private String actorName;

    @JsonProperty("alternative_actor_name")
    private String alternativeActorName;

    @JsonProperty("linked_actor")
    private List<String> linkedActor;


    @JsonProperty("actor_description")
    private String actorDescription;

    @JsonProperty("aggregation")
    private List<Aggregation> aggregationList;

    @JsonProperty("contact_details")
    private List<ContactDetails> contactDetails;

    @JsonProperty("actor_identifier")
    private List<ActorIdentifier> actorIdentifierList;

    @JsonProperty("actor_url")
    private List<ActorUrl> actorUrls;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("actor_technology_relationship")
    private List<ActorTechnologyRelationship> actorTechnologyRelationships;

    @JsonProperty("actor_trl_relationship")
    private List<ActorTRLRelationship> actorTRLRelationships;

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("actor_catalog_relationship")
    private List<ActorCatalogRelationship> actorCatalogRelationships;


    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getActorDescription() {
        return actorDescription;
    }

    public void setActorDescription(String actorDescription) {
        this.actorDescription = actorDescription;
    }

    public List<Aggregation> getAggregationList() {
        return aggregationList;
    }

    public void setAggregationList(List<Aggregation> aggregationList) {
        this.aggregationList = aggregationList;
    }

    public List<ContactDetails> getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(List<ContactDetails> contactDetails) {
        this.contactDetails = contactDetails;
    }

    public List<ActorUrl> getActorUrls() {
        return actorUrls;
    }

    public void setActorUrls(List<ActorUrl> actorUrls) {
        this.actorUrls = actorUrls;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<ActorTRLRelationship> getActorTRLRelationships() {
        return actorTRLRelationships;
    }

    public void setActorTRLRelationships(List<ActorTRLRelationship> actorTRLRelationships) {
        this.actorTRLRelationships = actorTRLRelationships;
    }


    public long getActorClassificationId() {
        return actorClassificationId;
    }

    public void setActorClassificationId(long actorClassificationId) {
        this.actorClassificationId = actorClassificationId;
    }

    public long getActorTypeId() {
        return actorTypeId;
    }

    public void setActorTypeId(long actorTypeId) {
        this.actorTypeId = actorTypeId;
    }

    public String getSmeIndustryService() {
        return smeIndustryService;
    }

    public void setSmeIndustryService(String smeIndustryService) {
        this.smeIndustryService = smeIndustryService;
    }

    public String getServiceProvided() {
        return serviceProvided;
    }

    public void setServiceProvided(String serviceProvided) {
        this.serviceProvided = serviceProvided;
    }

    public String getAlternativeActorName() {
        return alternativeActorName;
    }

    public void setAlternativeActorName(String alternativeActorName) {
        this.alternativeActorName = alternativeActorName;
    }

    public List<String> getLinkedActor() {
        return linkedActor;
    }

    public void setLinkedActor(List<String> linkedActor) {
        this.linkedActor = linkedActor;
    }

    public List<ActorIdentifier> getActorIdentifierList() {
        return actorIdentifierList;
    }

    public void setActorIdentifierList(List<ActorIdentifier> actorIdentifierList) {
        this.actorIdentifierList = actorIdentifierList;
    }


    public List<ActorCatalogRelationship> getActorCatalogRelationships() {
        return actorCatalogRelationships;
    }

    public void setActorCatalogRelationships(List<ActorCatalogRelationship> actorCatalogRelationships) {
        this.actorCatalogRelationships = actorCatalogRelationships;
    }
}

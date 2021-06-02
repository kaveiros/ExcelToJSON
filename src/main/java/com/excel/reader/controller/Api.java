package com.excel.reader.controller;

import com.excel.reader.ExcelToJson.ExcelRecord;
import com.excel.reader.ExcelToJson.MappingRecord;
import com.excel.reader.ExcelToJson.NullSerializer;
import com.excel.reader.ExcelToJson.RowRecord;
import com.excel.reader.engine.ExcelReader;
import com.excel.reader.engine.ExcelUtils;
import com.excel.reader.input.ExcelMergeRequest;
import com.excel.reader.input.ExcelRequest;
import com.excel.reader.model.Actor;
import com.excel.reader.model.ActorCatalogRelationship;
import com.excel.reader.model.ActorTRLRelationship;
import com.excel.reader.model.ActorTechnologyRelationship;
import com.excel.reader.model.ActorType;
import com.excel.reader.model.ActorUrl;
import com.excel.reader.model.Address;
import com.excel.reader.model.Aggregation;
import com.excel.reader.model.ContactDetails;
import com.excel.reader.model.ExcelToJson;
import com.excel.reader.model.Geometry;
import com.excel.reader.model.Location;
import com.excel.reader.model.Sector;
import com.excel.reader.model.SectorType;
import com.excel.reader.model.SectorTypeRelationship;
import com.excel.reader.model.Technology;
import com.excel.reader.model.TechnologyReadiness;
import com.excel.reader.model.TechnologyType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.poi.ss.usermodel.Sheet;
import org.codehaus.jackson.map.ser.StdSerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class Api {

    @Autowired
    private ExcelReader excelReader;

    @RequestMapping(path="/read-json", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> readJson(@RequestBody String payload) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        List<ExcelToJson> jsonList = new ArrayList<>();
        Actor actor = new Actor();
        //to be removed if id will be calculated somehow
        long generatedLong = Math.abs(new Random().nextLong());
        actor.setPlatformId(generatedLong);
        try {
            JsonNode actualObj = mapper.readTree(payload);
            for (JsonNode node : actualObj) {
                ExcelToJson excelToJson = new ExcelToJson();

                int actorTypeId = node.get("actor_type__id").asInt();
                excelToJson.setActorTypeId(actorTypeId);
                int actorType = node.get("actor_type").asInt();
                ActorType actorType1 = new ActorType();
                actorType1.setActorTypeId(actorTypeId);
                actorType1.setActorTypeLabel("");
                actorType1.setActorTypeDefinition("");
                actorType1.setActorTypeNotation("");
                actorType1.setActorTypeNotation("");

                excelToJson.setActorType(actorType1);
                String actorClassificationId = node.get("actor_classification__id").asText();
                String actorName = node.get("actor_name").asText();
                excelToJson.setActorName(actorName);
                String alternativeActorName = node.get("alternative_actor_name").asText();
                excelToJson.setAlternativeActorName(alternativeActorName);
                String actorDescription = node.get("actor_description").asText();
                excelToJson.setActorDescription(actorDescription);
                Iterator<Map.Entry<String, JsonNode>> aggregation  = node.get("aggregation").fields();
                List<Aggregation> aggregationList =  new ArrayList<>();
                while(aggregation.hasNext()) {
                    Map.Entry<String, JsonNode> ag = aggregation.next();
                    Aggregation aggregationObj = new Aggregation();
                    aggregationObj.setPlatformActorAccessUrl(ag.getValue().asText());
                    aggregationList.add(aggregationObj);
                }
                excelToJson.setAggregationList(aggregationList);
                Iterator<Map.Entry<String, JsonNode>> linkedActors = node.get("linked_actor").fields();
                List<String> linkedActorsList = new ArrayList<>();
                while(linkedActors.hasNext()){
                    Map.Entry<String, JsonNode> linkedA = linkedActors.next();
                    linkedActorsList.add(linkedA.getValue().textValue());
                }
                excelToJson.setLinkedActor(linkedActorsList);
                Iterator<JsonNode> actorUrlIt = node.get("actor_url").elements();
                List<ActorUrl> actorUrls = new ArrayList<>();
                while(actorUrlIt.hasNext()){
                    ActorUrl actorUrl =new ActorUrl();
                    JsonNode ac = actorUrlIt.next();
                    actorUrl.setActorUrl(ac.get("actor_url").asText());
                    actorUrls.add(actorUrl);
                }
                excelToJson.setActorUrls(actorUrls);

                ActorCatalogRelationship actorCatalogRelationship = new ActorCatalogRelationship();
                List<ActorCatalogRelationship> actorCatalogRelationships= new ArrayList<>();
                actorCatalogRelationships.add(actorCatalogRelationship);
                excelToJson.setActorCatalogRelationships(actorCatalogRelationships);

                Iterator<JsonNode> actorTechnologyRelationshipIt = node.get("actor_technology_relationship").elements();
                List<Technology> technologyList = new ArrayList<>();
                ActorTechnologyRelationship actorTechnologyRelationship = new ActorTechnologyRelationship();
                List<TechnologyType> technologyTypeList = new ArrayList<>();
                while (actorTechnologyRelationshipIt.hasNext()){
                    Technology technology = new Technology();
                    JsonNode tec = actorTechnologyRelationshipIt.next();
                   JsonNode technologyName =  tec.get("technology").get("technology_name");
                   technology.setTechnologyName(technologyName.asText());
                   technology.setGetTechnologyDescription("");
                   technology.setGetTechnologyDescription("");
                   technology.setTechnologyType(technologyTypeList);
                   technologyList.add(technology);

                }
                actorTechnologyRelationship.setTechnologyList(technologyList);
                List<ActorTechnologyRelationship> actorTechnologyRelationships = new ArrayList<>();
                actorTechnologyRelationships.add(actorTechnologyRelationship);
                excelToJson.setActorTechnologyRelationships(actorTechnologyRelationships);


//                Iterator<Map.Entry<String, JsonNode>> contactDetails = node.get("contact_details").fields();
//                List<ContactDetails> contactDetailsList = new ArrayList<>();
//                while(contactDetails.hasNext()) {
//                    ContactDetails contactDetails1  = new ContactDetails();
//
//                    Map.Entry<String, JsonNode> contactD = contactDetails.next();
//                    contactDetails1.setTelephone(contactD.getKey().);
//
//                }
                Iterator<JsonNode> location = node.get("location").elements();
                List<Address> addressList = new ArrayList<>();
                Geometry geometry = new Geometry();
                while (location.hasNext()){
                    Location location1 = new Location();
                    JsonNode loc = location.next();
                    location1.setGeographicName(loc.get("geographic_name").textValue());
                    location1.setGeographicIdentifier(loc.get("geographic_identifier").textValue());
                    Iterator<JsonNode> addresses = loc.get("address").elements();
                    while(addresses.hasNext()){
                        JsonNode adrs = addresses.next();
                        Address address = new Address();
                        if(adrs.get("full_address").textValue()!=null){

                            address.setAddress(adrs.get("full_address").textValue());
                        }

                        if(adrs.get("admin_unit__l1").asText()!=null){
                            address.setAdminUnitIdL1(adrs.get("admin_unit__l1").textValue());
                        }
                        if(adrs.get("admin_unit__l2").textValue()!=null){

                            address.setAdminUnitIdL2(adrs.get("admin_unit__l2").textValue());
                        }
                        if(adrs.get("state").textValue()!=null){

                            address.setState(adrs.get("state").textValue());
                        }
                        if (adrs.get("postal_code").textValue()!=null){

                            address.setPostalCode(adrs.get("postal_code").textValue());
                        }
                        addressList.add(address);
                    }
                    location1.setAddressList(addressList);

                    excelToJson.setLocation(location1);
                    JsonNode geo = loc.get("geometry");
                    String geoString = geo.asText();
                    if(geoString!=null && !geoString.equals("{}")){
                        String lat = StringUtils.substringBetween(geoString,"lat",",").replace(":", "").replace("\"", "");
                        String lon = StringUtils.substringBetween(geoString,"lng","}").replace(":", "").replace("\"", "");
                        geometry.setLat(lat);
                        geometry.setLon(lon);

                    }
                    location1.setGeometry(geometry);

                    Iterator<JsonNode> actorTLRIT = node.get("actor_trl_relationship").elements();
                    ActorTRLRelationship actorTRLRelationship = new ActorTRLRelationship();
                    List<TechnologyReadiness> technologyReadinessList = new ArrayList<>();
                    int counter = 1;
                    while(actorTLRIT.hasNext()){
                        TechnologyReadiness technologyReadiness = new TechnologyReadiness();
                        JsonNode actortrl = actorTLRIT.next();
                        JsonNode description = actortrl.get("technology_readiness_level");
                        String trl = StringUtils.substringBefore(description.get("technology_readiness_level").asText(), ":");
                        technologyReadiness.setTechnologyReadinessLevel(trl);
                        technologyReadiness.setTechnologyReadinessLevelId(counter);
                        technologyReadiness.setTechnologyReadinessLevelDescription(description.get("technology_readiness_level").asText());
                        counter++;
                        technologyReadinessList.add(technologyReadiness);

                    }
                    actorTRLRelationship.setTechnologyReadinessList(technologyReadinessList);
                    actorTRLRelationship.setTechnologyReadinessLevelId( Math.abs(new Random().nextLong()));
                    List<ActorTRLRelationship> actorTRLRelationshipList = new ArrayList<>();
                    actorTRLRelationshipList.add(actorTRLRelationship);
                    excelToJson.setActorTRLRelationships(actorTRLRelationshipList);

                    SectorTypeRelationship sectorTypeRelationship = new SectorTypeRelationship();
                    List<SectorTypeRelationship> sectorTypeRelationshipList = new ArrayList<>();
                    sectorTypeRelationshipList.add(sectorTypeRelationship);
                    Iterator<JsonNode> sectorTypeRelIt = node.get("actor_sector_relationship").elements();
                    List<SectorType> sectorTypeList = new ArrayList<>();
                    SectorType sectorType = new SectorType();
                    sectorTypeList.add(sectorType);
                    List<Sector> sectorList = new ArrayList<>();
                    while (sectorTypeRelIt.hasNext()){
                        JsonNode actorNode =sectorTypeRelIt.next();
                        System.out.println("");
                        Sector sector = new Sector();
                        sector.setSectorName(actorNode.get("sector_name").asText());
                        sector.setSectorDescription(actorNode.get("sector_description").asText());
                        sector.setSectorTypeList(sectorTypeList);
                        sectorList.add(sector);
                    }
                    sectorTypeRelationship.setSectorId( Math.abs(new Random().nextLong()));
                    sectorTypeRelationship.setSectorList(sectorList);
                    excelToJson.setSectorTypeRelationshipList(sectorTypeRelationshipList);



                    System.out.println("");
                }

                jsonList.add(excelToJson);

                //JsonNode f = mapper.reader().readTree(aggregation);




                System.out.println("");
            }
            actor.setActors(jsonList);
            System.out.println("");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(mapper.writeValueAsString(actor));
    }

    @RequestMapping(path="/generate-json", method = RequestMethod.POST , produces = "application/json")
    public ResponseEntity<String> generateJson (@RequestBody ExcelRequest request) throws JsonProcessingException {
        System.out.println(request.getFilePath());
        List<ExcelToJson> jsonList = excelReader.readExcelFile(request.getFilePath());
        Actor actor = new Actor();
        //to be removed if id will be calculated somehow
        long generatedLong = new Random().nextLong();
        actor.setPlatformId(generatedLong);
        actor.setActors(jsonList);

//        StdSerializerProvider sp = new StdSerializerProvider();
//        NullSerializer nullSerializer = new NullSerializer();
        //sp.setNullValueSerializer(nullSerializer);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        return ResponseEntity.ok(mapper.writeValueAsString(actor));
    }


    @RequestMapping(path = "/map", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> mapFields(@RequestBody ExcelMergeRequest request) {

        Sheet sheet = excelReader.readExcel(request.getExcelToMerge(), request.getSheetName());//read sheet from excel containing values.
        Sheet originalSheet = excelReader.readExcel(request.getOriginalExcel(), null);//read inital sheet
        List<RowRecord> originalRecords = excelReader.readSheetToBeMapped(originalSheet);//read records from initial records.
        List<MappingRecord> mappingRecordList = getMappingRecords(sheet);

        //get entries tha
        ExcelUtils excelUtils = new ExcelUtils();
        List<ExcelRecord> replacementRecords = new ArrayList<>();
        fillReplacementRecords(originalRecords, mappingRecordList, excelUtils, replacementRecords);

        return ResponseEntity.ok("Mapping completed");
    }

    private void fillReplacementRecords(List<RowRecord> originalRecords, List<MappingRecord> mappingRecordList, ExcelUtils excelUtils, List<ExcelRecord> replacementRecords) {
        for (MappingRecord mappingRecord : mappingRecordList) {
            String id = mappingRecord.getId();
            String mappingString = mappingRecord.getValue();

            for (RowRecord record : originalRecords) {
                List<ExcelRecord> p = excelUtils.getRowEntriesAccordingToId(record.getExcelRecordsPerRow(), id);
                if(!p.isEmpty()){
                    System.out.println("p size " + p.size());
                    List<ExcelRecord> re = excelUtils.getEntriesForMapping(p, "TECHNOLOGY / TECHNOLOGY_NAME");
                    for(ExcelRecord ex : re) {
                        if (ex.getExcel().contains(mappingString)) {
                            ex.setContent(mappingString);
                            replacementRecords.add(ex);
                        }
                    }
                    System.out.println("re size"+ re.size());

                }
            }
            System.out.println("");
        }
    }

    private List<MappingRecord> getMappingRecords(Sheet sheet) {
        List<RowRecord> recordsToBeMapped  = excelReader.readSheetToBeMapped(sheet);//read all records from sheet to be mapped.
        List<MappingRecord> mappingRecordList = new ArrayList<>();
        //add all records from sheet to be mapped to a list
        for(RowRecord rowRecord: recordsToBeMapped) {
            MappingRecord mappingRecord = new MappingRecord();
            if(rowRecord.getExcelRecordsPerRow().size() == 2 ){
                mappingRecord.setId(rowRecord.getExcelRecordsPerRow().get(1).getContent());
                mappingRecord.setValue(rowRecord.getExcelRecordsPerRow().get(0).getContent());
                mappingRecordList.add(mappingRecord);
            }
        }
        return mappingRecordList;
    }
}

package com.excel.reader.engine;

import com.excel.reader.ExcelToJson.ExcelRecord;
import com.excel.reader.ExcelToJson.RowRecord;
import com.excel.reader.enums.TechnologyReadinessEnum;
import com.excel.reader.model.ActorTRLRelationship;
import com.excel.reader.model.ActorUrl;
import com.excel.reader.model.Address;
import com.excel.reader.model.Aggregation;
import com.excel.reader.model.ContactDetails;
import com.excel.reader.model.ExcelToJson;
import com.excel.reader.model.Geometry;
import com.excel.reader.model.Location;
import com.excel.reader.model.Technology;
import com.excel.reader.model.TechnologyReadiness;
import org.apache.commons.lang3.StringUtils;
import org.apache.xalan.xsltc.runtime.ErrorMessages_zh;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelUtils {

    protected  long calculateArraySection(List<ExcelRecord> records, String section) {
        return records.stream()
                .filter(p -> p.getDb().equals(section))
                .count();
    }

    public List<ExcelRecord> getEntries(List<ExcelRecord> records, String section) {

        List<ExcelRecord> records1=  records.stream().filter(p -> p.getDb().contains(section)).filter(p->p.getContent()!=null).collect(Collectors.toList());
        return records1;
    }


    public List<ExcelRecord> getEntriesForMapping(List<ExcelRecord> records, String section) {
        List<ExcelRecord> entries = records.stream().filter(p->p.getDb().equals(section)).collect(Collectors.toList());
        return entries;
    }

    public List<ExcelRecord> getRowEntriesAccordingToId(List<ExcelRecord> records, String id) {
        List<ExcelRecord> entries = records.stream().filter(p->p.getHubId()!=null).filter(p->p.getHubId().trim().equals(id))
                .collect(Collectors.toList());
        return entries;
    }

    protected List<ExcelToJson> generateJSON(List<RowRecord> rowRecords){


        List<ExcelToJson> jsonPerRowList = new ArrayList<>();
        Map<String, String> addressMap = new HashMap<>();
        //Check the cell type and format accordingly
        for(RowRecord rowRecord : rowRecords){

        ExcelToJson json = new ExcelToJson();

            getSingleValues(json, addressMap, rowRecord);


            List<ActorUrl> actorUrlList = getActorUrls(rowRecord);
            json.setActorUrls(actorUrlList);
            ActorTRLRelationship actorTRLRelationship = new ActorTRLRelationship();
            actorTRLRelationship.setTechnologyReadinessList(getTechnologyReadinessLevel(rowRecord));
            List<ActorTRLRelationship> actorTRLRelationships = new ArrayList<>();
            actorTRLRelationships.add(actorTRLRelationship);
            json.setActorTRLRelationships(actorTRLRelationships);


            List<ContactDetails> contactDetails = getContactDetails(rowRecord);
            json.setContactDetails(contactDetails);
            List<Address> addressList = getAddressesForJson(addressMap);
            Location location = new Location();
            location.setAddressList(addressList);
            location.setGeometry(new Geometry());
            json.setLocation(location);
            jsonPerRowList.add(json);
           // json.setContactDetails(contactDetailsList);
        }


        return jsonPerRowList;
    }

    private void getSingleValues(ExcelToJson json, Map<String, String> addressMap, RowRecord rowRecord) {
        for(ExcelRecord record : rowRecord.getExcelRecordsPerRow()) {
            switch (record.getDb()) {
                case "ACTOR / ACTOR_NAME":
                    json.setActorName(record.getContent());
                    break;
                case "ACTOR / ACTOR_DESCRIPTION (Append under the main description)":
                    if(record.getExcel().equals("Services")){
                        json.setServices(record.getContent());
                    }
                    else if (record.getExcel().equals("Equipment")) {
                        json.setEquipment(record.getContent());
                    }
                    //aggregation.setPlatformActorAccessUrl(cell.getStringCellValue());
                    // aggregationList.add(aggregation);
                    // json.setAggregationList(aggregationList);
                    //System.out.print("AGGREGATION" + cell.getStringCellValue() + "  ");

                    break;
                case "AGGREGATION / PLATFORM_ACTOR_ACCESS_URL":
                    List<Aggregation> aggregationList = new ArrayList<>();
                    Aggregation aggregation = new Aggregation();
                    aggregation.setPlatformActorAccessUrl(record.getContent());
                    aggregationList.add(aggregation);
                    json.setAggregationList(aggregationList);
                    break;
                case "ACTOR / ACTOR_DESCRIPTION":
                    json.setActorDescription(record.getContent());
                    break;

                case "ADDRESS / ADMIN_UNIT_L1":
                    if(record.getContent()!=null && !record.getContent().isEmpty()) {

                        addressMap.put("ADDRESS / ADMIN_UNIT_L1" + record.getRow(),record.getContent());
                    }
                    break;
                case "ADDRESS / ADMIN_UNIT_L2":
                    if(record.getContent()!=null && !record.getContent().isEmpty()) {

                        addressMap.put("ADDRESS / ADMIN_UNIT_L2" + record.getRow(),record.getContent());
                    }
                    break;
                case "ADDRESS / STATE":
                    addressMap.put("ADDRESS / STATE", record.getContent());
                    break;
                case "TECHNOLOGY / TECHNOLOGY_NAME":
                    Technology technology = new Technology();
                    technology.setTechnologyName(record.getContent());

                    break;


            }



        }//end of for record
    }

    private List<ActorUrl> getActorUrls(RowRecord rowRecord) {
        List<ActorUrl> actorUrlList = new ArrayList<>();
        List<ExcelRecord> actorUrls = getEntries(rowRecord.getExcelRecordsPerRow(), "ACTOR_URL / ACTOR_URL");
        for(ExcelRecord actor: actorUrls) {
            ActorUrl actorUrl = new ActorUrl();
            actorUrl.setActorUrl(actor.getContent());
            actorUrlList.add(actorUrl);

        }
        return actorUrlList;
    }

    private List<TechnologyReadiness> getTechnologyReadinessLevel(RowRecord rowRecord) {
        List<TechnologyReadiness> technologyReadinessList = new ArrayList<>();
        List<ExcelRecord> entries = getEntries(rowRecord.getExcelRecordsPerRow(), "TRL ");
        long counter = 0;
        for(ExcelRecord record : entries) {
            if(record.getContent()!=null && record.getContent().equals("X")) {
                counter++;
                String trlValue = StringUtils.substringBefore(record.getExcel(), ":").replaceAll("\\s+","");
                TechnologyReadiness readiness = new TechnologyReadiness();
                readiness.setTechnologyReadinessLevelId(counter);
                String t = TechnologyReadinessEnum.valueOf(trlValue).getLabel();
                readiness.setTechnologyReadinessLevel(trlValue);
                readiness.setTechnologyReadinessLevelDescription(t);
                technologyReadinessList.add(readiness);
            }

        }
        return technologyReadinessList;
    }

    private List<ContactDetails> getContactDetails(RowRecord rowRecord) {
        List<Integer> sizes = new ArrayList<>();

        List<ContactDetails> contactDetailsList = new ArrayList<>();
        List<ExcelRecord> contactDetailsNames = getEntries(rowRecord.getExcelRecordsPerRow(), "CONTACT_DETAILS / NAME");
        sizes.add(contactDetailsNames.size());
        List<ExcelRecord> contactDetaisEmail = getEntries(rowRecord.getExcelRecordsPerRow(), "CONTACT_DETAILS / EMAIL");
        sizes.add(contactDetaisEmail.size());
        List<ExcelRecord> telephones = getEntries(rowRecord.getExcelRecordsPerRow(), "TELEPHONE");
        sizes.add(telephones.size());
        List<ExcelRecord> positions = getEntries(rowRecord.getExcelRecordsPerRow(), "POSITION");
        sizes.add(positions.size());
        int max = Collections.max(sizes);
        for (int i = 0; i<max; i++) {
            ContactDetails contactDetails = new ContactDetails();
            if(contactDetaisEmail.size() > i) {
                contactDetails.setEmail(contactDetaisEmail.get(i).getContent());
            }
            if(contactDetailsNames.size() > i) {
                contactDetails.setName(contactDetailsNames.get(i).getContent());
            }
            if(positions.size()> i) {
                contactDetails.setPosition(positions.get(i).getContent());

            }
            if (telephones.size() > i) {

                contactDetails.setTelephone(telephones.get(i).getContent());
            }
            contactDetailsList.add(contactDetails);
        }

        return contactDetailsList;
    }

    private List<Address> getAddressesForJson(Map<String, String> addressMap) {
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        for (Map.Entry<String, String> addressEntry: addressMap.entrySet()) {
            if(addressEntry.getKey().contains("ADDRESS / FULL_ADDRESS")){
                address.setAddress(addressEntry.getValue());
            }
            if (addressEntry.getKey().contains("ADDRESS / ADMIN_UNIT_L2")) {
                address.setAdminUnitIdL2(addressEntry.getValue());
            }
            if (addressEntry.getKey().contains("ADDRESS / ADMIN_UNIT_L1")) {
                address.setAdminUnitIdL1(addressEntry.getValue());
            }
            if (addressEntry.getKey().contains("ADDRESS / STATE")) {
                address.setState(addressEntry.getValue());
            }
        }
        addresses.add(address);
        return addresses;
    }


    private List<Technology> getTechnologies(RowRecord rowRecord){
        return null;
    }

}

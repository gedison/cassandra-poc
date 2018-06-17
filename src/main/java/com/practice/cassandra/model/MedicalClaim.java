package com.practice.cassandra.model;

import com.datastax.driver.mapping.annotations.Table;

import java.util.List;
import java.util.Map;

@Table(name = "medicalClaim")
public class MedicalClaim {

    private String id;
    private String packageId;
    private String name;
    private Map<String, String> additionalProperties;
    private List<ValidationRecord> validationRecords;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Map<String, String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public List<ValidationRecord> getValidationRecords() {
        return validationRecords;
    }

    public void setValidationRecords(List<ValidationRecord> validationRecords) {
        this.validationRecords = validationRecords;
    }

    @Override
    public String toString() {
        return "MedicalClaim{" +
                "id='" + id + '\'' +
                ", packageId='" + packageId + '\'' +
                ", name='" + name + '\'' +
                ", additionalProperties=" + additionalProperties +
                ", validationRecords=" + validationRecords +
                '}';
    }
}

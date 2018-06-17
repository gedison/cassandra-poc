package com.practice.cassandra.model;

import com.datastax.driver.mapping.annotations.UDT;

@UDT(keyspace = "mykeyspace", name = "validationrecord")
public class ValidationRecord {

    private String fieldName;
    private int errorCode;
    private int errorLevel;

    public ValidationRecord(){

    }

    public ValidationRecord(String fieldName, int errorCode, int errorLevel){
        this.fieldName = fieldName;
        this.errorCode = errorCode;
        this.errorLevel = errorLevel;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(int errorLevel) {
        this.errorLevel = errorLevel;
    }

    @Override
    public String toString() {
        return "ValidationRecord{" +
                "fieldName='" + fieldName + '\'' +
                ", errorCode=" + errorCode +
                ", errorLevel=" + errorLevel +
                '}';
    }
}

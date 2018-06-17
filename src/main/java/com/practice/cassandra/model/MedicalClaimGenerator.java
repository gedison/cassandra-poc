package com.practice.cassandra.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class MedicalClaimGenerator {

    public static MedicalClaim create(){
        MedicalClaim ret = new MedicalClaim();
        ret.setId(UUID.randomUUID().toString());
        ret.setPackageId("12345");
        ret.setName(RandomStringUtils.randomAlphabetic(8));
        ret.setAdditionalProperties(new HashMap<>());
        ret.getAdditionalProperties().put(RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5));
        ret.setValidationRecords(new ArrayList<>());
        ret.getValidationRecords().add(new ValidationRecord(RandomStringUtils.random(5), new Random().nextInt(), new Random().nextInt()));
        return ret;
    }
}

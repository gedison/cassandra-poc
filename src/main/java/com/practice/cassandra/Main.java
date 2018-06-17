package com.practice.cassandra;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.practice.cassandra.config.CassandraConfig;
import com.practice.cassandra.model.MedicalClaim;
import com.practice.cassandra.repository.MedicalClaimRepository;

import java.util.List;
import java.util.logging.Logger;


public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String KEYSPACE = "mykeyspace";

    public static void main(String args[]){
        List<MedicalClaim> medicalClaims = null;
        Session session = CassandraConfig.session(CassandraConfig.cluster(), KEYSPACE);
        MappingManager mappingManager = CassandraConfig.mappingManager(session);
        MedicalClaimRepository medicalClaimRepository = new MedicalClaimRepository(session, mappingManager);

        medicalClaims = medicalClaimRepository.getMedicalClaimsPagination("12345", 1,1);
        LOGGER.info("SIZE: "+medicalClaims.size());
        for(int i=0; i<medicalClaims.size(); i++){
            LOGGER.info(i+": "+medicalClaims.get(i).toString());
        }

        medicalClaims = medicalClaimRepository.getMedicalClaimsPagination("12345", 25,1);
        LOGGER.info("SIZE: "+medicalClaims.size());
        for(int i=0; i<medicalClaims.size(); i++){
            LOGGER.info(i+": "+medicalClaims.get(i).toString());
        }

        medicalClaims = medicalClaimRepository.getMedicalClaimsPagination("12345", 5,1);
        LOGGER.info("SIZE: "+medicalClaims.size());
        for(int i=0; i<medicalClaims.size(); i++){
            LOGGER.info(i+": "+medicalClaims.get(i).toString());
        }

        return;


    }
}

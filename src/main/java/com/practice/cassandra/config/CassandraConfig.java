package com.practice.cassandra.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

public class CassandraConfig {

    public static Cluster cluster(){
        return Cluster.builder()
                .addContactPoint("localhost")
                .withPort(32784)
                .build();
    }

    public static Session session(Cluster cluster, String keyspace){
        return cluster.connect(keyspace);
    }

    public static MappingManager mappingManager(Session session){
        return new MappingManager(session);
    }
}

package com.practice.cassandra.repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.practice.cassandra.model.MedicalClaim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MedicalClaimRepository {

    private static final String GET_ALL = "SELECT * FROM medicalClaim;";
    private static final String GET = "SELECT * FROM medicalClaim WHERE id = ?";
    private static final String CREATE = "INSERT INTO medicalClaim json ?";
    private static final String CREATE_2 = "INSERT INTO medicalClaim (id,packageId,name,additionalProperties,validationRecords) VALUES (?,?,?,?,?)";

    private Session session;
    private Mapper<MedicalClaim> mapper;
    private Gson gson;

    public MedicalClaimRepository(Session session, MappingManager mappingManager) {
        this.session = session;
        mapper = mappingManager.mapper(MedicalClaim.class);
        this.gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    }

    public List<MedicalClaim> getMedicalClaims(String packageId, int pageSize, int pageNumber) {
        ResultSet resultSet = session.execute(GET_ALL);
        return mapper.map(resultSet).all();
    }

    public List<MedicalClaim> getMedicalClaimsPagination(String packageId, int pageSize, int pageNumber) {
        int offset = (pageNumber - 1);

        Statement statement = new SimpleStatement(GET_ALL);
        statement.setFetchSize(pageSize);
        ResultSet resultSet = session.execute(statement);
        PagingState nextPage = resultSet.getExecutionInfo().getPagingState();

        while ((offset-- > 0) && !resultSet.isFullyFetched()) {
            statement.setPagingState(nextPage);
            resultSet = session.execute(statement);
            nextPage = resultSet.getExecutionInfo().getPagingState();
        }

        int remaining = (offset > 0) ? 0 : resultSet.getAvailableWithoutFetching();
        Iterator<MedicalClaim> iterator = mapper.map(resultSet).iterator();
        List<MedicalClaim> medicalClaims = new ArrayList<>();
        while(iterator.hasNext() && remaining-- > 0){
            medicalClaims.add(iterator.next());
        }

        return medicalClaims;
    }

    public MedicalClaim getMedicalClaim(String id) {
        ResultSet resultSet = session.execute(GET, id);
        return mapper.map(resultSet).one();
    }

    public void createMedicalClaims(Set<MedicalClaim> medicalClaims) {
        PreparedStatement preparedStatement = session.prepare(CREATE);
        BatchStatement batch = new BatchStatement();

        medicalClaims.forEach(claim -> {
            String json = gson.toJson(claim);
            batch.add(preparedStatement.bind(json));
        });

        session.execute(batch);
    }

    public void createMedicalClaims2(Set<MedicalClaim> medicalClaims) {
        PreparedStatement preparedStatement = session.prepare(CREATE_2);

        medicalClaims.forEach(claim -> {
            session.execute(preparedStatement.bind(claim.getId(), claim.getPackageId(), claim.getName(), claim.getAdditionalProperties(), claim.getValidationRecords()));
        });
    }
}

package com.example.mongo.repository;

import com.example.mongo.domain.WorkFlowConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFlowConfigRepository extends MongoRepository<WorkFlowConfig, String> {

    @Query(value = " { 'workFlowConfigSteps.id' :  :#{#stepId} } ")
    WorkFlowConfig findByStep(@Param("stepId") String stepId);
}

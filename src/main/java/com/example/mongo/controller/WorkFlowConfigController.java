package com.example.mongo.controller;

import com.example.mongo.domain.WorkFlowConfig;
import com.example.mongo.dto.WorkFlowConfigDto;
import com.example.mongo.dto.WorkFlowConfigStep;
import com.example.mongo.service.WorkFlowConfigService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portal")
public class WorkFlowConfigController {

    private final WorkFlowConfigService workFlowConfigService;
    private final MongoTemplate mongoTemplate;

    public WorkFlowConfigController(WorkFlowConfigService workFlowConfigService, MongoTemplate mongoTemplate) {
        this.workFlowConfigService = workFlowConfigService;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping("/save")
    public ResponseEntity<WorkFlowConfigDto> save(@RequestBody WorkFlowConfigDto workFlowConfigDto) {
        return workFlowConfigService.save(workFlowConfigDto);
    }

    @GetMapping("/get-by-step/{stepId}")
    public ResponseEntity<WorkFlowConfigStep> getByStep(@PathVariable("stepId") String stepId) {
        return workFlowConfigService.getByStep(stepId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<WorkFlowConfig>> getAll() {
        Query query = new Query();
        query.addCriteria(new Criteria().orOperator(Criteria.where("name").is("Sample WF"), Criteria.where("status").regex("Active")));
        query.addCriteria(Criteria.where("workFlowConfigSteps.entityName").is("Registration"));
        List<WorkFlowConfig> workFlowConfigList = mongoTemplate.find(query, WorkFlowConfig.class, "workflow_config");
        return new ResponseEntity<>(workFlowConfigList, HttpStatus.valueOf(HttpStatus.OK.value()));
    }
}

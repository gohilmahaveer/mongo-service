package com.example.mongo.service;

import com.example.mongo.domain.WorkFlowConfig;
import com.example.mongo.dto.WorkFlowConfigDto;
import com.example.mongo.dto.WorkFlowConfigStep;
import com.example.mongo.mapper.WorkFlowConfigMapper;
import com.example.mongo.repository.WorkFlowConfigRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkFlowConfigService {

    private final WorkFlowConfigMapper workFlowConfigMapper;
    private final WorkFlowConfigRepository workFlowConfigRepository;

    public WorkFlowConfigService(WorkFlowConfigMapper workFlowConfigMapper, WorkFlowConfigRepository workFlowConfigRepository) {
        this.workFlowConfigMapper = workFlowConfigMapper;
        this.workFlowConfigRepository = workFlowConfigRepository;
    }

    public ResponseEntity<WorkFlowConfigDto> save(WorkFlowConfigDto workFlowConfigDto) {
        WorkFlowConfig workFlowConfig = workFlowConfigMapper.toDomain(workFlowConfigDto);
        return new ResponseEntity<>(workFlowConfigMapper.toDto(workFlowConfigRepository.save(workFlowConfig)), HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }

    public ResponseEntity<WorkFlowConfigStep> getByStep(String stepId) {
        WorkFlowConfig workFlowConfig = workFlowConfigRepository.findByStep(stepId);
        if (null != workFlowConfig) {
            WorkFlowConfigStep workFlowConfigStep = workFlowConfig.getWorkFlowConfigSteps().stream().filter(data -> data.getId().equals(stepId)).findFirst().orElse(null);
            if (null != workFlowConfigStep) {
                return new ResponseEntity<>(workFlowConfigStep, HttpStatus.valueOf(HttpStatus.OK.value()));
            } else {
                return new ResponseEntity<>(null, HttpStatus.valueOf(HttpStatus.OK.value()));
            }
        }
        return new ResponseEntity<>(null, HttpStatus.valueOf(HttpStatus.OK.value()));
    }
}

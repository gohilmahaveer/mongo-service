package com.example.mongo.domain;

import com.example.mongo.dto.WorkFlowConfigStep;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "workflow_config")
@Getter
@Setter
public class WorkFlowConfig {

    @Id
    private String id;
    private String name;
    private String status;
    private List<WorkFlowConfigStep> workFlowConfigSteps;
}

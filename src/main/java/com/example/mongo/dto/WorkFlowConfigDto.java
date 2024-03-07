package com.example.mongo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkFlowConfigDto {

    private String id;
    private String name;
    private String status;
    private List<WorkFlowConfigStep> workFlowConfigSteps;
}

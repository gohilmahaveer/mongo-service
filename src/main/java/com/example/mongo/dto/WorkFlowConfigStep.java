package com.example.mongo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkFlowConfigStep {

    private String id;
    private String stepOrder;
    private String entityName;
}

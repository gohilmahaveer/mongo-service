package com.example.mongo.mapper;

import com.example.mongo.domain.WorkFlowConfig;
import com.example.mongo.dto.WorkFlowConfigDto;
import org.mapstruct.Mapper;

@Mapper
public interface WorkFlowConfigMapper {

    WorkFlowConfig toDomain(WorkFlowConfigDto workFlowConfigDto);
    WorkFlowConfigDto toDto(WorkFlowConfig workFlowConfig);
}
package com.mrlii.tddtaskmanagementapplication.mappers;

import com.mrlii.tddtaskmanagementapplication.model.dto.TaskDto;
import com.mrlii.tddtaskmanagementapplication.model.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto dto);
}
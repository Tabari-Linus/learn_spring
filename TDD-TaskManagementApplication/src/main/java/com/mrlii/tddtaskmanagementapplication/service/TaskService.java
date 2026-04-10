package com.mrlii.tddtaskmanagementapplication.service;

import com.mrlii.tddtaskmanagementapplication.exception.TaskNotFoundException;
import com.mrlii.tddtaskmanagementapplication.mappers.TaskMapper;
import com.mrlii.tddtaskmanagementapplication.model.entity.Task;
import com.mrlii.tddtaskmanagementapplication.model.dto.TaskDto;
import com.mrlii.tddtaskmanagementapplication.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskDto createTask(TaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.title())
                .status(taskDto.status())
                .build();
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    public TaskDto updateTaskStatus(long id, String status) {
        Task taskToUpdate = getTask(id);
        taskToUpdate.setStatus(status);
        Task savedTask = taskRepository.save(taskToUpdate);
        return taskMapper.toDto(savedTask);
    }

    public void deleteTask(long id) {
        getTask(id);
        taskRepository.deleteById(id);
    }

    public TaskDto getTaskById(long id) {
        return taskMapper.toDto(getTask(id));
    }

    private Task getTask(long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task not found with id: " + id)
        );
    }

    public List<TaskDto> getAllTask() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public TaskDto updateTask(Long id, String title) {
        Task task = getTask(id);
        task.setTitle(title);
        Task savedTask = taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }
}

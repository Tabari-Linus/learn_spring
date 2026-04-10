package com.mrlii.tddtaskmanagementapplication.controller;

import com.mrlii.tddtaskmanagementapplication.model.dto.TaskDto;
import com.mrlii.tddtaskmanagementapplication.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTask();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        TaskDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDto savedTask = taskService.createTask(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/update-status")
    public ResponseEntity<TaskDto> updateTaskStatus(@RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTaskStatus(taskDto.id(), taskDto.status());
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/update-title")
    public ResponseEntity<TaskDto> updateTaskTitle(@RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(taskDto.id(), taskDto.title());
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTask(@RequestBody TaskDto taskDto) {
        taskService.deleteTask(taskDto.id());
        return ResponseEntity.noContent().build();
    }
}

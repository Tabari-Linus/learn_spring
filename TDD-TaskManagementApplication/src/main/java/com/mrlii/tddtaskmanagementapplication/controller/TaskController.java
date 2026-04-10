package com.mrlii.tddtaskmanagementapplication.controller;

import com.mrlii.tddtaskmanagementapplication.model.entity.Task;
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
    public ResponseEntity<List<Task>> getAllTasks() {
       List<Task> tasks = taskService.getAllTask();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task savedTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/update-status")
    public ResponseEntity<Task> updateTaskStatus(@RequestBody Task task) {
        Task updatedTask = taskService.updateTaskStatus(task.getId(), task.getStatus());
        return ResponseEntity.ok(updatedTask);
    }

    @PutMapping("/update-title")
    public ResponseEntity<Task> updateTaskTitle(@RequestBody Task task) {
        Task updatedTask = taskService.updateTask(task.getId(), task.getTitle());
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTask(@RequestBody Task task) {
        taskService.deleteTask(task.getId());
        return ResponseEntity.noContent().build();
    }


}

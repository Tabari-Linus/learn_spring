package com.mrlii.tddtaskmanagementapplication.service;

import com.mrlii.tddtaskmanagementapplication.exception.TaskNotFoundException;
import com.mrlii.tddtaskmanagementapplication.model.entity.Task;
import com.mrlii.tddtaskmanagementapplication.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTaskStatus(long id, String status) {
        Task taskToUpdate = getTask(id);
        taskToUpdate.setStatus(status);
        return taskRepository.save(taskToUpdate);
    }


    public void deleteTask(long id) {
        getTask(id);
        taskRepository.deleteById(id);
    }

    public Task getTaskById(long id) {
        return getTask(id);
    }

    private Task getTask(long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new TaskNotFoundException("Task not found with id: " + id)
        );
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, String title) {
        Task task = getTask(id);
        task.setTitle(title);
        return taskRepository.save(task);
    }
}

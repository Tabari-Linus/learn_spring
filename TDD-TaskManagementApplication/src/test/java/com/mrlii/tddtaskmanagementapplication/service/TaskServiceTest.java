package com.mrlii.tddtaskmanagementapplication.service;

import com.mrlii.tddtaskmanagementapplication.exception.TaskNotFoundException;
import com.mrlii.tddtaskmanagementapplication.model.entity.Task;
import com.mrlii.tddtaskmanagementapplication.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @Test
    void testCreateTask(){
        Task task = Task.builder()
                .title("Cook beans")
                .status("Done")
                .build();
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        Task savedTask = taskService.createTask(task);
        assertNotNull(savedTask);
        assertEquals("Cook beans", savedTask.getTitle());

        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testGetTaskById_TaskNotFound(){

        when(taskRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(1L));
    }

    @Test
    void testUpdateTaskStatus(){

        Task task = Task.builder()
                .id(1L)
                .title("Cook beans")
                .status("Done")
                .build();

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updateTask = taskService.updateTaskStatus(1l, "In Progress");

        assertNotNull(updateTask);
        assertEquals("In Progress", updateTask.getStatus());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testDeleteTask(){
        Task task = Task.builder()
                .id(1L)
                .title("Cook beans")
                .status("Done")
                .build();
        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteTask_TaskNotFound(){
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(1L));
    }

    @Test
    void testGetAllTask(){
        List<Task> tasks = Arrays.asList(
                Task.builder().id(1L).title("Cook beans").status("Done").build(),
                Task.builder().id(2L).title("Cook rice").status("In Progress").build()
        );

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> retrievedTask = taskService.getAllTask();

        assertNotNull(retrievedTask);
        assertEquals(2, retrievedTask.size());
        verify(taskRepository, times(1)).findAll();
    }


    @Test
    void testUpdateTask(){

        Task task = Task.builder()
                .id(1L)
                .title("Cook beans")
                .status("Done")
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task updatedTask = taskService.updateTask(1L, "Cook Red Beans");
        assertNotNull(updatedTask);
        assertEquals("Cook Red Beans", updatedTask.getTitle());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }
}

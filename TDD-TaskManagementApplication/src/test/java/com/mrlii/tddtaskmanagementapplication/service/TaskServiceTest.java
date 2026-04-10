package com.mrlii.tddtaskmanagementapplication.service;

import com.mrlii.tddtaskmanagementapplication.exception.TaskNotFoundException;
import com.mrlii.tddtaskmanagementapplication.mappers.TaskMapper;
import com.mrlii.tddtaskmanagementapplication.model.entity.Task;
import com.mrlii.tddtaskmanagementapplication.model.dto.TaskDto;
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

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskService taskService;

    @Test
    void testCreateTask(){
        TaskDto taskDto = new TaskDto(null, "Cook beans", "Done");
        Task savedTaskEntity = Task.builder()
                .id(1L)
                .title("Cook beans")
                .status("Done")
                .build();
        TaskDto savedTaskDto = new TaskDto(1L, "Cook beans", "Done");

        when(taskRepository.save(any(Task.class))).thenReturn(savedTaskEntity);
        when(taskMapper.toDto(any(Task.class))).thenReturn(savedTaskDto);

        TaskDto savedTask = taskService.createTask(taskDto);
        assertNotNull(savedTask);
        assertEquals("Cook beans", savedTask.title());

        verify(taskRepository, times(1)).save(any(Task.class));
        verify(taskMapper, times(1)).toDto(any(Task.class));
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
        TaskDto updatedTaskDto = new TaskDto(1L, "Cook beans", "In Progress");

        when(taskRepository.findById(task.getId())).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.toDto(any(Task.class))).thenReturn(updatedTaskDto);

        TaskDto updateTask = taskService.updateTaskStatus(1L, "In Progress");

        assertNotNull(updateTask);
        assertEquals("In Progress", updateTask.status());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
        verify(taskMapper, times(1)).toDto(any(Task.class));
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
        when(taskMapper.toDto(any(Task.class))).thenReturn(new TaskDto(1L, "Cook beans", "Done"));

        List<TaskDto> retrievedTask = taskService.getAllTask();

        assertNotNull(retrievedTask);
        assertEquals(2, retrievedTask.size());
        verify(taskRepository, times(1)).findAll();
        verify(taskMapper, times(2)).toDto(any(Task.class));
    }

    @Test
    void testUpdateTask(){
        Task task = Task.builder()
                .id(1L)
                .title("Cook beans")
                .status("Done")
                .build();
        TaskDto updatedTaskDto = new TaskDto(1L, "Cook Red Beans", "Done");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.toDto(any(Task.class))).thenReturn(updatedTaskDto);

        TaskDto updatedTask = taskService.updateTask(1L, "Cook Red Beans");
        assertNotNull(updatedTask);
        assertEquals("Cook Red Beans", updatedTask.title());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
        verify(taskMapper, times(1)).toDto(any(Task.class));
    }
}

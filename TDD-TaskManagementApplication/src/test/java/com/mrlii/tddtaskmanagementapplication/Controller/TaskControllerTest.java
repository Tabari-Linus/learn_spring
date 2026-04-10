package com.mrlii.tddtaskmanagementapplication.Controller;

import com.mrlii.tddtaskmanagementapplication.controller.TaskController;
import com.mrlii.tddtaskmanagementapplication.model.dto.TaskDto;
import com.mrlii.tddtaskmanagementapplication.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @MockitoBean
    TaskService taskService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetAllTasks() throws Exception {
        List<TaskDto> tasks = Arrays.asList(
                new TaskDto(1L, "Cook beans", "Done"),
                new TaskDto(2L, "Cook rice", "In Progress")
        );

        when(taskService.getAllTask()).thenReturn(tasks);

        mockMvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Cook beans"))
                .andExpect(jsonPath("$[1].title").value("Cook rice"));
    }

    @Test
    void testGetTaskById() throws Exception {
        TaskDto task = new TaskDto(1L, "Cook beans", "Done");

        when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Cook beans"))
                .andExpect(jsonPath("$.status").value("Done"));
    }

    @Test
    void testCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto(null, "Cook beans", "Done");
        TaskDto savedTask = new TaskDto(1L, "Cook beans", "Done");

        when(taskService.createTask(taskDto)).thenReturn(savedTask);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Cook beans"))
                .andExpect(jsonPath("$.status").value("Done"));
    }

    @Test
    void testCreateTask_InvalidRequestInput() throws Exception {
        TaskDto taskDto = new TaskDto(null, "", "Done");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateTaskStatus() throws Exception {
        TaskDto requestTask = new TaskDto(1L, null, "In Progress");
        TaskDto updatedTask = new TaskDto(1L, "Cook beans", "In Progress");

        when(taskService.updateTaskStatus(1L, "In Progress")).thenReturn(updatedTask);

        mockMvc.perform(put("/tasks/update-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("In Progress"))
                .andExpect(jsonPath("$.title").value("Cook beans"));
    }

    @Test
    void testUpdateTaskTitle() throws Exception {
        TaskDto requestTask = new TaskDto(1L, "Cook rice", null);
        TaskDto updatedTask = new TaskDto(1L, "Cook rice", "Done");

        when(taskService.updateTask(1L, "Cook rice")).thenReturn(updatedTask);

        mockMvc.perform(put("/tasks/update-title")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestTask)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Cook rice"))
                .andExpect(jsonPath("$.status").value("Done"));
    }

    @Test
    void testDeleteTask() throws Exception {
        TaskDto requestTask = new TaskDto(1L, null, null);

        doNothing().when(taskService).deleteTask(1L);

        mockMvc.perform(delete("/tasks/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestTask)))
                .andExpect(status().isNoContent());
    }
}

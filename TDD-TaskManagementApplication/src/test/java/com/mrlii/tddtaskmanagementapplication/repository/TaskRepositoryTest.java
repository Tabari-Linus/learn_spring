package com.mrlii.tddtaskmanagementapplication.repository;

import com.mrlii.tddtaskmanagementapplication.model.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void testSaveTask(){
        Task task = Task.builder()
                .title("Cook beans")
                .status("Done")
                .build();

        Task savedTask = taskRepository.save(task);

        assertNotNull(savedTask.getTitle());
        assertEquals("Cook beans", savedTask.getTitle());

    }

    @Test
    void testDeletedTask(){
        Task task = Task.builder()
                .title("Cook beans")
                .status("Done")
                .build();

        taskRepository.save(task);

        taskRepository.delete(task);
        Optional<Task> deletedTask = taskRepository.findById(task.getId());

        assertFalse(deletedTask.isPresent());
    }

    @Test
    void testFindById(){
        Task task = Task.builder()
                .title("Cook beans")
                .status("Done")
                .build();
        taskRepository.save(task);
        Optional<Task> foundTask = taskRepository.findById(task.getId());
        assertTrue(foundTask.isPresent());
    }

    @Test
    void testFindAllTask(){
        Task task = Task.builder()
                .title("Cook beans")
                .status("Done")
                .build();
        taskRepository.save(task);

        Task task2 = Task.builder()
                .title("Cook rice")
                .status("In Progress")
                .build();
        taskRepository.save(task2);
        List<Task> tasks = taskRepository.findAll();
        assertEquals(2, tasks.size());
    }

    @Test
    void testUpdateTask(){
        Task task = Task.builder()
                .title("Cook beans")
                .status("Done")
                .build();
        taskRepository.save(task);

        task.setTitle("Cook rice");
        task.setStatus("In Progress");

        taskRepository.save(task);
        Optional<Task> updatedTask = taskRepository.findById(task.getId());

        assertTrue(updatedTask.isPresent());
        assertEquals("Cook rice", updatedTask.get().getTitle());
        assertEquals("In Progress", updatedTask.get().getStatus());
    }
}

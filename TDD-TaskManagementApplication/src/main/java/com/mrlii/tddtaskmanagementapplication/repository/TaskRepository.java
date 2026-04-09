package com.mrlii.tddtaskmanagementapplication.repository;

import com.mrlii.tddtaskmanagementapplication.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

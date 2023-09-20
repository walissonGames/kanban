package com.kanban.wall.taskActivity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskActivityRepository extends JpaRepository<TaskActivity, Long> {
    
}

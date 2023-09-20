package com.kanban.wall.taskComment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRespository extends JpaRepository<TaskComment, Long>{
    
}

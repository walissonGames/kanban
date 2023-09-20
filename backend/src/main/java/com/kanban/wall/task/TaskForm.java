package com.kanban.wall.task;

import java.time.LocalDateTime;
import com.kanban.wall.enums.TaskStatus;

public record TaskForm (
    Long id,
    String name,
    String description,
    LocalDateTime deadline,
    Long kanbanId,
    TaskStatus status
){

}

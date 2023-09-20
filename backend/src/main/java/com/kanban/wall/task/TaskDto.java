package com.kanban.wall.task;

import java.time.LocalDateTime;

public record TaskDto (
    Long id,
    String name,
    String description,
    LocalDateTime deadline,
    Long kanbanId,
    String status
){
    public TaskDto(Task task){
        this(task.getId(),
             task.getName(),
             task.getDescription(),
             task.getDeadline(),
             task.getKanban().getId(),
             task.getStatus().name()
        );
    }
}

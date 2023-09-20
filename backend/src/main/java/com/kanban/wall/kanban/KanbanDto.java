package com.kanban.wall.kanban;

import java.time.LocalDateTime;

public record KanbanDto (
    Long id,
    String name,
    LocalDateTime creationDate,
    Long creatorId
){
    public KanbanDto(Kanban kanban){
        this(kanban.getId(), kanban.getName(), kanban.getCreationDate(), -1L);
    }
}

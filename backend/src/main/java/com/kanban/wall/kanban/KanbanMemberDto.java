package com.kanban.wall.kanban;

import com.kanban.wall.user.User;

public record KanbanMemberDto (
    Long kanbanId,
    Long userId
){
    public KanbanMemberDto(Kanban kanban, User user){
        this(kanban.getId(), user.getId());
    }
}

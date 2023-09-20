package com.kanban.wall.task;

import com.kanban.wall.user.User;

public record TaskContributorDto (
    Long taskId,
    Long userId
){
    public TaskContributorDto(Task task, User user){
        this(task.getId(), user.getId());
    }
}

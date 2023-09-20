package com.kanban.wall.taskComment;

import java.time.LocalDateTime;

public record TaskCommentDto (
    Long id,
    String comment,
    String image,
    LocalDateTime creationDate,
    Long taskId
){
    public TaskCommentDto(TaskComment taskComment){
        this(taskComment.getId(),
             taskComment.getComment(),
             taskComment.getImageBase64(),
             taskComment.getCreationDate(),
             taskComment.getTask().getId());
    }
}

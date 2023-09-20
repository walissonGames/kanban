package com.kanban.wall.taskComment;

public record TaskCommentForm (
    Long id,
    String comment,
    String image,
    Long taskId
){
    
}

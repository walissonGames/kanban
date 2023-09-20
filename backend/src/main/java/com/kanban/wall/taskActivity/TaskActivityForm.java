package com.kanban.wall.taskActivity;

public record TaskActivityForm (
    Long id,
    String title,
    boolean isCompleted,
    Long taskId
){
    
}

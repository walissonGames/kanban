package com.kanban.wall.taskActivity;

public record TaskActivityDto (
    Long id,
    String title,
    boolean isCompleted,
    Long taskId
){
    public TaskActivityDto(TaskActivity taskActivity){
        this(taskActivity.getId(),
             taskActivity.getTitle(),
             taskActivity.isCompleted(),
             taskActivity.getTask().getId());
    }
}

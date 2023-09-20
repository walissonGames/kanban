package com.kanban.wall.taskComment;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanban.wall.task.Task;
import com.kanban.wall.task.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskCommentService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCommentRespository taskCommentRespository;

    public TaskCommentDto save(TaskCommentForm form) throws IOException {
        byte[] image = null;
        try {
            image = Base64.getDecoder().decode(form.image());
        } catch (Exception e) { }
         
        Task task = taskRepository.getReferenceById(form.taskId());
        TaskComment taskComment = new TaskComment(form.comment(), image, task);
        taskCommentRespository.save(taskComment);
        return new TaskCommentDto(taskComment);
    }

    public TaskCommentDto getTaskComment(Long id){
        TaskComment taskComment = taskCommentRespository.getReferenceById(id);
        return new TaskCommentDto(taskComment);
    }

    public TaskCommentDto updateTaskComment(TaskCommentForm form) throws IOException{
        byte[] image = null;
        try {
            image = Base64.getDecoder().decode(form.image());
        } catch (Exception e) { }
        
        TaskComment taskComment = taskCommentRespository.getReferenceById(form.id());
        taskComment.updateData(form.comment(), image);
        return new TaskCommentDto(taskComment);
    }

    public void deleteTaskComment(Long id){
        taskCommentRespository.deleteById(id);
    }
}

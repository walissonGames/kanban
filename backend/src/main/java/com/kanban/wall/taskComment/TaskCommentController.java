package com.kanban.wall.taskComment;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/task/comment")
public class TaskCommentController {
    
    @Autowired
    private TaskCommentService taskCommentService;

    @PostMapping
    public ResponseEntity<TaskCommentDto> create(@RequestBody TaskCommentForm taskCommentForm, UriComponentsBuilder uriBuilder) throws IOException{
        TaskCommentDto taskCommentDto = taskCommentService.save(taskCommentForm);
        URI uri = uriBuilder.path("/api/task/comment/{id}").buildAndExpand(taskCommentDto.id()).toUri();
        return ResponseEntity.created(uri).body(taskCommentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCommentDto> get(@PathVariable Long id){
        TaskCommentDto taskCommentDto = taskCommentService.getTaskComment(id);
        return ResponseEntity.ok(taskCommentDto);
    }

    @PutMapping
    public ResponseEntity<TaskCommentDto> update(@RequestBody TaskCommentForm form) throws IOException{
        TaskCommentDto taskCommentDto = taskCommentService.updateTaskComment(form);
        return ResponseEntity.ok(taskCommentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        taskCommentService.deleteTaskComment(id);
        return ResponseEntity.noContent().build();
    }
}

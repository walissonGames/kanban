package com.kanban.wall.taskActivity;

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
@RequestMapping("/api/task/activity")
public class TaskActivityController {
    
    @Autowired
    private TaskActivityService taskActivityService;

    @PostMapping
    public ResponseEntity<TaskActivityDto> create(@RequestBody TaskActivityForm form, UriComponentsBuilder uriBuilder){
        TaskActivityDto taskActivityDto = taskActivityService.save(form);
        URI uri = uriBuilder.path("/api/task/activity/{id}").buildAndExpand(taskActivityDto.id()).toUri();
        return ResponseEntity.created(uri).body(taskActivityDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskActivityDto> get(@PathVariable Long id){
        TaskActivityDto taskActivityDto = taskActivityService.getActivity(id);
        return ResponseEntity.ok(taskActivityDto);
    }

    @PutMapping
    public ResponseEntity<TaskActivityDto> update(@RequestBody TaskActivityForm form){
        TaskActivityDto taskActivityDto = taskActivityService.update(form);
        return ResponseEntity.ok(taskActivityDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        taskActivityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.kanban.wall.task;

import java.net.URI;
import java.util.List;

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

import com.kanban.wall.taskActivity.TaskActivityDto;
import com.kanban.wall.taskComment.TaskCommentDto;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> create(@RequestBody TaskForm form, UriComponentsBuilder uriBuilder){
        TaskDto taskDto = taskService.save(form);
        URI uri = uriBuilder.path("/api/task/{id}").buildAndExpand(taskDto.id()).toUri();
        return ResponseEntity.created(uri).body(taskDto);
    }

    @PostMapping("/contributor")
    public ResponseEntity<TaskContributorDto> addTeamMember(@RequestBody TaskContributorForm form){
        TaskContributorDto taskContributorDto = taskService.addTaskContributor(form.taskId(), form.userId());
        return ResponseEntity.ok().body(taskContributorDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> get(@PathVariable Long id){
        TaskDto taskDto = taskService.getTask(id);
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/list-activities/{id}")
    public ResponseEntity<List<TaskActivityDto>> getActivities(@PathVariable Long id){
        List<TaskActivityDto> list = taskService.getActivities(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/list-comments/{id}")
    public ResponseEntity<List<TaskCommentDto>> getComments(@PathVariable Long id){
        List<TaskCommentDto> list = taskService.getComments(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/contributor/{id}")
    public ResponseEntity<List<TaskContributorDto>> getContributors(@PathVariable Long id){
        List<TaskContributorDto> contributors = taskService.getTaskContributors(id);
        return ResponseEntity.ok(contributors);
    }

    @PutMapping
    public ResponseEntity<TaskDto> update(@RequestBody TaskForm form){
        TaskDto taskDto = taskService.updateTask(form);
        return ResponseEntity.ok(taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}

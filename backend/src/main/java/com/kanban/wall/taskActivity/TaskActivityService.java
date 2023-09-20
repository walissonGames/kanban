package com.kanban.wall.taskActivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanban.wall.task.Task;
import com.kanban.wall.task.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskActivityService {
    
    @Autowired
    private TaskActivityRepository taskActivityRepository;

    @Autowired
    private TaskRepository taskRepository;

    public TaskActivityDto save(TaskActivityForm form){
        Task task = taskRepository.getReferenceById(form.taskId());
        TaskActivity taskActivity = new TaskActivity(form.title(), task);
        taskActivityRepository.save(taskActivity);

        return new TaskActivityDto(taskActivity);
    }

    public TaskActivityDto getActivity(Long id){
        TaskActivity taskActivity = taskActivityRepository.getReferenceById(id);
        return new TaskActivityDto(taskActivity);
    }

    public TaskActivityDto update(TaskActivityForm form){
        TaskActivity taskActivity = taskActivityRepository.getReferenceById(form.id());
        taskActivity.updateData(form.title(), form.isCompleted());
        return new TaskActivityDto(taskActivity);
    }

    public void deleteById(Long id){
        taskActivityRepository.deleteById(id);
    }
}

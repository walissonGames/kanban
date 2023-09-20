package com.kanban.wall.task;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanban.wall.kanban.Kanban;
import com.kanban.wall.kanban.KanbanRepository;
import com.kanban.wall.taskActivity.TaskActivityDto;
import com.kanban.wall.taskComment.TaskCommentDto;
import com.kanban.wall.user.User;
import com.kanban.wall.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KanbanRepository kanbanRepository;

    @Autowired
    private UserRepository userRepository;

    public TaskDto save(TaskForm form){
        Kanban kanban = kanbanRepository.getReferenceById(form.kanbanId());
        Task task = new Task(form.name(), form.description(), form.deadline(), kanban, form.status());
        taskRepository.save(task);

        return new TaskDto(task);
    }

    public TaskDto getTask(Long id){
        Task task = taskRepository.getReferenceById(id);
        return new TaskDto(task);
    }

    public List<TaskActivityDto> getActivities(Long id){
        Task task = taskRepository.getReferenceById(id);
        return task.getTaskActivity().stream().map(TaskActivityDto::new).collect(Collectors.toList());
    }

    public List<TaskCommentDto> getComments(Long id) {
        Task task = taskRepository.getReferenceById(id);
        return task.getTaskComment().stream().map(TaskCommentDto::new).collect(Collectors.toList());
    }

    @Transactional
    public TaskDto updateTask(TaskForm form){
        Task task = taskRepository.getReferenceById(form.id());
        task.updateData(form.name(), form.description(), form.deadline(), form.status());
        return new TaskDto(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public TaskContributorDto addTaskContributor(Long taskId, Long userId){
        Task task = taskRepository.getReferenceById(taskId);
        User user = userRepository.getReferenceById(userId);
        task.addContributor(user);
        return new TaskContributorDto(task, user);
    }

    public List<TaskContributorDto> getTaskContributors(Long taskId){
        Task task = taskRepository.getReferenceById(taskId);
        List<TaskContributorDto> contributors = task.getContributors().stream()
                                                    .map(user -> new TaskContributorDto(task, user))
                                                    .collect(Collectors.toList());
        return contributors;
    }
}

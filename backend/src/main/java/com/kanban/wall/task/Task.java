package com.kanban.wall.task;

import com.kanban.wall.enums.TaskStatus;
import com.kanban.wall.kanban.Kanban;
import com.kanban.wall.taskActivity.TaskActivity;
import com.kanban.wall.taskComment.TaskComment;
import com.kanban.wall.user.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tb_task")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "kanbanId")
    private Kanban kanban;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private TaskStatus status;

    @OneToMany(mappedBy = "task")
    private Set<TaskActivity> taskActivity = new HashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<TaskComment> taskComment = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tb_taskContributor",
        joinColumns = @JoinColumn(name = "taskId"),
        inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> contributors = new HashSet<>();

    public Task(String name, String description, LocalDateTime deadline, Kanban kanban, TaskStatus status){
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.kanban = kanban;
        this.status = status;
    }

    public void updateData(String name, String description, LocalDateTime deadline, TaskStatus status){
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

    public void addContributor(User user){
        contributors.add(user);
    }
}

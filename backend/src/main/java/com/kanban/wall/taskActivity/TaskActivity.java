package com.kanban.wall.taskActivity;

import com.kanban.wall.task.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_taskActivity")
@Getter
@NoArgsConstructor
public class TaskActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isCompleted")
    private boolean isCompleted = false;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    public TaskActivity(String title, Task task) {
        this.title = title;
        this.task = task;
        this.isCompleted = false;
    }

    public void updateData(String title, boolean isCompleted){
        this.title = title;
        this.isCompleted = isCompleted;
    }
}

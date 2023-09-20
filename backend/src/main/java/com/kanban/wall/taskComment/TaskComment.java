package com.kanban.wall.taskComment;

import java.time.LocalDateTime;
import java.util.Base64;

import com.kanban.wall.task.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_taskComment")
@Getter
@NoArgsConstructor
public class TaskComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "taskId")
    private Task task;

    public String getImageBase64(){
        try {
            return Base64.getEncoder().encodeToString(image);
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }

    public TaskComment(String comment, byte[] image, Task task){
        this.comment = comment;
        this.image = image;
        this.task = task;
        this.creationDate = LocalDateTime.now();
    }

    public void updateData(String comment, byte[] image){
        this.comment = comment;
        this.image = image;
    }
}

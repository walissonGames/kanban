package com.kanban.wall.user;

import java.util.HashSet;
import java.util.Set;

import com.kanban.wall.kanban.Kanban;
import com.kanban.wall.task.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tb_user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "encryptedPassword")
    private String encryptedPassword;

    @ManyToMany(mappedBy = "team")
    private Set<Kanban> kanbans = new HashSet<>();

    @ManyToMany(mappedBy = "contributors")
    private Set<Task> contributions = new HashSet<>();
}

package com.kanban.wall.kanban;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.kanban.wall.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_kanban")
@Getter
@NoArgsConstructor
public class Kanban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "creatorId")
    private User creatorId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tb_kanbanTeam",
        joinColumns = @JoinColumn(name = "kanbanId"),
        inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> team = new HashSet<>();

    public Kanban(String name, User user){
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.creatorId = user;
    }

    public void updateData(String name){
        if(name != null)
            this.name = name;
    }

    public void addTeamMember(User user){
        team.add(user);
    }
}

package dz.corepulse.projectflow.Models.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
public class Project extends AbstractEntity {

    private String projectName;
    private String statut;
    private String description;
    private Integer progress;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    @OneToMany(mappedBy = "project")
    private List<Epic> epics = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Sprint> sprints = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userList = new ArrayList<>();
}

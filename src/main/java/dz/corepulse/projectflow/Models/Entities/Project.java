package dz.corepulse.projectflow.Models.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
}

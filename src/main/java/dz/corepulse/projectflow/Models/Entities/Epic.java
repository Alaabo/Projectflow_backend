package dz.corepulse.projectflow.Models.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "epic")
@Getter
@Setter
public class Epic extends AbstractEntity {

    private String epicName;
    private String description;
    private String statut;
    private String priority;
    private Integer progress;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer tempPasse;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "epic")
    private List<Story> stories = new ArrayList<>();
}

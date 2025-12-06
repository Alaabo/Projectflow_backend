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
@Table(name = "story")
@Getter
@Setter
public class Story extends AbstractEntity {

    private String storyName;
    private String desc;
    private String statut;
    private String priority;
    private Integer pts;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer timeLeft;

    @ManyToOne
    private Epic epic;

    @ManyToOne
    private Sprint sprint;

    @OneToMany(mappedBy = "story")
    private List<Task> tasks = new ArrayList<>();
}


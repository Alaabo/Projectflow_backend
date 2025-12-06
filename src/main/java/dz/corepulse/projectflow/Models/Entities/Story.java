package dz.corepulse.projectflow.Models.Entities;

import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private StoryStatus statut = StoryStatus.TODO;
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

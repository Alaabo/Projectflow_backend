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
@Table(name = "task")
@Getter
@Setter
public class Task extends AbstractEntity {

    private String taskName;
    private String desc;
    private String statut;
    private String priority;

    private LocalDateTime dateDebut;
    private Integer timeLeft;
    private Integer timeEstimate;
    private String tag;

    @ManyToOne
    private Story story;

    @ManyToOne
    private Sprint sprint;

    @ManyToOne
    private User assignedUser;

    @OneToMany(mappedBy = "task")
    private List<SubTask> subtasks = new ArrayList<>();
}


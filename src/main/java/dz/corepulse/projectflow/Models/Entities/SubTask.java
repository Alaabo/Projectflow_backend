package dz.corepulse.projectflow.Models.Entities;

import dz.corepulse.projectflow.Models.Enums.SubTaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subtask")
@Getter
@Setter
public class SubTask extends AbstractEntity {

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private SubTaskStatus statut = SubTaskStatus.TODO;

    @ManyToOne
    private Task task;
}

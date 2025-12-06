package dz.corepulse.projectflow.Models.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subtask")
@Getter
@Setter
public class SubTask extends AbstractEntity {

    private String name;
    private String desc;
    private String statut;

    @ManyToOne
    private Task task;
}


package dz.corepulse.projectflow.Models.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "privilege")
@Getter
@Setter
public class Privilege extends AbstractEntity {

    private String name;

    // If "privilege" is a separate field, keep it
    private String privilege;
}

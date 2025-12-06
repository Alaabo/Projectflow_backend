package dz.corepulse.projectflow.Models.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class Profile extends AbstractEntity {

    private String name;

    @ManyToMany
    @JoinTable(
            name = "profile_privilege",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private List<Privilege> privileges = new ArrayList<>();
}


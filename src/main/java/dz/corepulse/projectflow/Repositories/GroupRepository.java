package dz.corepulse.projectflow.Repositories;

import dz.corepulse.projectflow.Models.Entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
}
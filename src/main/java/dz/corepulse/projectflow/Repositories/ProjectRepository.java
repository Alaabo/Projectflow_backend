package dz.corepulse.projectflow.Repositories;

import dz.corepulse.projectflow.Models.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}

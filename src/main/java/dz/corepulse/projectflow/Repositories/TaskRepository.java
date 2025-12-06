package dz.corepulse.projectflow.Repositories;

import dz.corepulse.projectflow.Models.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}

package dz.corepulse.projectflow.Repositories;

import dz.corepulse.projectflow.Models.Entities.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubTaskRepository extends JpaRepository<SubTask, UUID> {
}
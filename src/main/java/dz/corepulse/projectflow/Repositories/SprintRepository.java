package dz.corepulse.projectflow.Repositories;

import dz.corepulse.projectflow.Models.Entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface SprintRepository extends JpaRepository<Sprint, UUID>, JpaSpecificationExecutor<Sprint> {

    List<Sprint> findByProjectIdOrderByDateDebutDesc(UUID projectId);
}

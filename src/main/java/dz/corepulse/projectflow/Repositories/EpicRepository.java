package dz.corepulse.projectflow.Repositories;

import dz.corepulse.projectflow.Models.Entities.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface EpicRepository extends JpaRepository<Epic, UUID>, JpaSpecificationExecutor<Epic> {
}

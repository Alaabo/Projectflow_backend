package dz.corepulse.projectflow.Repositories;

import dz.corepulse.projectflow.Models.Entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface StoryRepository extends JpaRepository<Story, UUID>, JpaSpecificationExecutor<Story> {
}

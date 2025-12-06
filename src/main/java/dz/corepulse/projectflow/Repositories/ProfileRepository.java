package dz.corepulse.projectflow.Repositories;

import dz.corepulse.projectflow.Models.Entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}


package dz.corepulse.projectflow.Repositories;


import dz.corepulse.projectflow.Models.Entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID> {
}

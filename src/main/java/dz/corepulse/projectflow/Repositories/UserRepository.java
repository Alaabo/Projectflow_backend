package dz.corepulse.projectflow.Repositories;


import dz.corepulse.projectflow.Models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Optional shortcuts
    boolean existsByName(String name);

    User findByName(String name);
}

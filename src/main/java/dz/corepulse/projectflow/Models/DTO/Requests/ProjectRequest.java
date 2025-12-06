package dz.corepulse.projectflow.Models.DTO.Requests;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ProjectRequest {

    private String projectName;
    private String statut;
    private String description;
    private Integer progress;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private List<UUID> userIds;
}

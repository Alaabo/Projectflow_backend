package dz.corepulse.projectflow.Models.DTO.Responses;



import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProjectResponse {

    private UUID id;

    private String projectName;
    private String statut;
    private String description;
    private Integer progress;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


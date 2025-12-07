package dz.corepulse.projectflow.Models.DTO.Responses;



import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SprintResponse {

    private UUID id;

    private String sprintName;
    private String description;
    private String statut;
    private String priority;
    private Integer progress;
    private Integer tempPasse;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private UUID projectId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

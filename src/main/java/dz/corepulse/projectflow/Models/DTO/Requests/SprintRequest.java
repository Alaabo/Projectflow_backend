package dz.corepulse.projectflow.Models.DTO.Requests;



import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SprintRequest {

    private String sprintName;
    private String desc;
    private String statut;
    private String priority;
    private Integer progress;
    private Integer tempPasse;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private UUID projectId;
}


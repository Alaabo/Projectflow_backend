package dz.corepulse.projectflow.Models.DTO.Requests;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProjectRequest {

    private String projectName;
    private String statut;
    private String description;
    private Integer progress;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
}


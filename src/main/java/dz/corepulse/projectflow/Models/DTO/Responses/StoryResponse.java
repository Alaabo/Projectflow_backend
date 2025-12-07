package dz.corepulse.projectflow.Models.DTO.Responses;

import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StoryResponse {

    private UUID id;

    private String storyName;
    private String description;
    private StoryStatus statut;
    private String priority;
    private Integer pts;
    private Integer timeLeft;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private UUID epicId;
    private UUID sprintId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

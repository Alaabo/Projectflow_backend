package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Sprint details returned to clients.")
public class SprintResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Sprint name")
    private String sprintName;

    @Schema(description = "Sprint goal or description")
    private String description;

    @Schema(description = "Workflow status")
    private String statut;

    @Schema(description = "Priority value")
    private String priority;

    @Schema(description = "Completion percentage")
    private Integer progress;

    @Schema(description = "Time spent in hours")
    private Integer tempPasse;

    @Schema(description = "Start date")
    private LocalDateTime dateDebut;

    @Schema(description = "End date")
    private LocalDateTime dateFin;

    @Schema(description = "Owning project identifier")
    private UUID projectId;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}

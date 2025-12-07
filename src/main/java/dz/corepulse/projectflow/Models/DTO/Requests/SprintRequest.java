package dz.corepulse.projectflow.Models.DTO.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Payload for creating or updating a sprint.")
public class SprintRequest {

    @Schema(description = "Sprint name", example = "Sprint 18")
    private String sprintName;

    @Schema(description = "Sprint goal or description", example = "Stabilize reporting module")
    private String description;

    @Schema(description = "Workflow status", example = "OPEN")
    private String statut;

    @Schema(description = "Priority indicator", example = "MEDIUM")
    private String priority;

    @Schema(description = "Progress percent", example = "10")
    private Integer progress;

    @Schema(description = "Time spent in hours", example = "24")
    private Integer tempPasse;

    @Schema(description = "Start date", example = "2024-03-01T09:00:00")
    private LocalDateTime dateDebut;

    @Schema(description = "Planned finish date", example = "2024-03-14T18:00:00")
    private LocalDateTime dateFin;

    @Schema(description = "Project identifier owning the sprint")
    private UUID projectId;
}

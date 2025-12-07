package dz.corepulse.projectflow.Models.DTO.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Payload for creating or updating a project.")
public class ProjectRequest {

    @Schema(description = "Human readable project name", example = "Corepulse Platform")
    private String projectName;

    @Schema(description = "Lifecycle status of the project", example = "ACTIVE")
    private String statut;

    @Schema(description = "Short project description", example = "Backlog for the Corepulse web platform")
    private String description;

    @Schema(description = "Completion percentage between 0 and 100", example = "25")
    private Integer progress;

    @Schema(description = "Start date", example = "2024-01-01T08:00:00")
    private LocalDateTime dateDebut;

    @Schema(description = "End date", example = "2024-03-31T17:00:00")
    private LocalDateTime dateFin;

    @Schema(description = "Identifiers of users to associate with the project")
    private List<UUID> userIds;
}

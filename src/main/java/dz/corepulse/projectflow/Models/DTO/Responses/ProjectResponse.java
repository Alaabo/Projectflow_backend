package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Project details returned to clients.")
public class ProjectResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Project name")
    private String projectName;

    @Schema(description = "Workflow status")
    private String statut;

    @Schema(description = "Project description")
    private String description;

    @Schema(description = "Completion percent")
    private Integer progress;

    @Schema(description = "Start date")
    private LocalDateTime dateDebut;

    @Schema(description = "End date")
    private LocalDateTime dateFin;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;

    @Schema(description = "Identifiers of linked users")
    private List<UUID> userIds;
}

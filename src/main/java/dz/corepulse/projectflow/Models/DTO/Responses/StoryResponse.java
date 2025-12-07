package dz.corepulse.projectflow.Models.DTO.Responses;

import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Story resource returned by the API.")
public class StoryResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Story title")
    private String storyName;

    @Schema(description = "Story description")
    private String description;

    @Schema(description = "Current status")
    private StoryStatus statut;

    @Schema(description = "Priority level")
    private String priority;

    @Schema(description = "Story points")
    private Integer pts;

    @Schema(description = "Remaining time in hours")
    private Integer timeLeft;

    @Schema(description = "Start date")
    private LocalDateTime dateDebut;

    @Schema(description = "End date")
    private LocalDateTime dateFin;

    @Schema(description = "Parent epic identifier")
    private UUID epicId;

    @Schema(description = "Sprint identifier")
    private UUID sprintId;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}

package dz.corepulse.projectflow.Models.DTO.Requests;

import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Payload for creating or updating a story.")
public class StoryRequest {

    @Schema(description = "Story title", example = "As a user I can reset my password")
    private String storyName;

    @Schema(description = "Story description or acceptance criteria")
    private String description;

    @Schema(description = "Initial story status", example = "TODO")
    private StoryStatus statut;

    @Schema(description = "Priority indicator", example = "HIGH")
    private String priority;

    @Schema(description = "Story points", example = "8")
    private Integer pts;

    @Schema(description = "Time remaining in hours", example = "16")
    private Integer timeLeft;

    @Schema(description = "Start date", example = "2024-03-02T08:00:00")
    private LocalDateTime dateDebut;

    @Schema(description = "Due date", example = "2024-03-05T18:00:00")
    private LocalDateTime dateFin;

    @Schema(description = "Parent epic identifier")
    private UUID epicId;

    @Schema(description = "Sprint identifier (optional)")
    private UUID sprintId;
}

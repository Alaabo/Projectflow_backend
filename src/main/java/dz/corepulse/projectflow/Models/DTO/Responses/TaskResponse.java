package dz.corepulse.projectflow.Models.DTO.Responses;

import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Task representation returned by the API.")
public class TaskResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Task name")
    private String taskName;

    @Schema(description = "Task description")
    private String description;

    @Schema(description = "Current status")
    private TaskStatus statut;

    @Schema(description = "Priority level")
    private String priority;

    @Schema(description = "Start date")
    private LocalDateTime dateDebut;

    @Schema(description = "Remaining time in hours")
    private Integer timeLeft;

    @Schema(description = "Estimated time in hours")
    private Integer timeEstimate;

    @Schema(description = "Tag for grouping tasks")
    private String tag;

    @Schema(description = "Owning story identifier")
    private UUID storyId;

    @Schema(description = "Sprint identifier")
    private UUID sprintId;

    @Schema(description = "Assigned user identifier")
    private UUID assignedUserId;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}

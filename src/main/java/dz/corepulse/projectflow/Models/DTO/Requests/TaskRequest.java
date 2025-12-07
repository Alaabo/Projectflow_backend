package dz.corepulse.projectflow.Models.DTO.Requests;

import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Payload for creating or updating a task.")
public class TaskRequest {

    @Schema(description = "Task name", example = "Implement login form")
    private String taskName;

    @Schema(description = "Task description", example = "Build accessibility compliant login form")
    private String description;

    @Schema(description = "Initial status", example = "TODO")
    private TaskStatus statut;

    @Schema(description = "Priority indicator", example = "HIGH")
    private String priority;

    @Schema(description = "Start date", example = "2024-03-04T09:00:00")
    private LocalDateTime dateDebut;

    @Schema(description = "Time remaining in hours", example = "6")
    private Integer timeLeft;

    @Schema(description = "Estimated time in hours", example = "12")
    private Integer timeEstimate;

    @Schema(description = "Optional tag for filtering", example = "frontend")
    private String tag;

    @Schema(description = "Owning story identifier")
    private UUID storyId;

    @Schema(description = "Sprint identifier (optional)")
    private UUID sprintId;

    @Schema(description = "Assignee identifier", example = "d7d2c1f2-5aa9-4fc2-96fa-5c2c7bd1b915")
    private UUID assignedUserId;
}

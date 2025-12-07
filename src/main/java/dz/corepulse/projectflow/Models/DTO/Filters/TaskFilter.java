package dz.corepulse.projectflow.Models.DTO.Filters;

import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Query parameters for filtering tasks.")
public class TaskFilter {

    @Schema(description = "Filter by task status")
    private TaskStatus statut;

    @Schema(description = "Filter by priority", example = "HIGH")
    private String priority;

    @Schema(description = "Limit to a specific story")
    private UUID storyId;

    @Schema(description = "Limit to a specific sprint")
    private UUID sprintId;
}

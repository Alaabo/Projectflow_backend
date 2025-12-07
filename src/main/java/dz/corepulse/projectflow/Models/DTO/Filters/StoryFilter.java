package dz.corepulse.projectflow.Models.DTO.Filters;

import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Query parameters for filtering stories.")
public class StoryFilter {

    @Schema(description = "Filter by status")
    private StoryStatus statut;

    @Schema(description = "Filter by priority", example = "MEDIUM")
    private String priority;

    @Schema(description = "Limit to stories inside a sprint")
    private UUID sprintId;
}

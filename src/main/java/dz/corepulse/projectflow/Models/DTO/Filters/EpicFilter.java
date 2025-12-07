package dz.corepulse.projectflow.Models.DTO.Filters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Query parameters for filtering epics.")
public class EpicFilter {

    @Schema(description = "Filter by status", example = "PLANNED")
    private String statut;

    @Schema(description = "Filter by priority", example = "HIGH")
    private String priority;
}

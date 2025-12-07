package dz.corepulse.projectflow.Models.DTO.Filters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(description = "Query parameters for filtering projects.")
public class ProjectFilter {

    @Schema(description = "Filter by workflow status", example = "ACTIVE")
    private String statut;

    @Schema(description = "Filter projects starting after this date", example = "2024-01-01T00:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateDebutFrom;

    @Schema(description = "Filter projects starting before this date", example = "2024-12-31T23:59:59")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateDebutTo;
}

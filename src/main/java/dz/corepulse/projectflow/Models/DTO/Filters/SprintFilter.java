package dz.corepulse.projectflow.Models.DTO.Filters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(description = "Query parameters for filtering sprints.")
public class SprintFilter {

    @Schema(description = "Filter by workflow status", example = "OPEN")
    private String statut;

    @Schema(description = "Start after date", example = "2024-03-01T00:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateDebutFrom;

    @Schema(description = "Start before date", example = "2024-03-31T23:59:59")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateDebutTo;

    @Schema(description = "Finish after date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateFinFrom;

    @Schema(description = "Finish before date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateFinTo;
}

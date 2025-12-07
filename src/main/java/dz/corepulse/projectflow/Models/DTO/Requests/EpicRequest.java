package dz.corepulse.projectflow.Models.DTO.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Payload used to create or update an epic.")
public class EpicRequest {

    @Schema(description = "Display name for the epic", example = "Authentication overhaul")
    private String epicName;

    @Schema(description = "Epic description", example = "Tracks the end-to-end auth refactor")
    private String description;

    @Schema(description = "Workflow status", example = "PLANNED")
    private String statut;

    @Schema(description = "Priority level", example = "HIGH")
    private String priority;

    @Schema(description = "Percent complete for the epic", example = "40")
    private Integer progress;

    @Schema(description = "Time already spent (in hours)", example = "12")
    private Integer tempPasse;

    @Schema(description = "Planned start date", example = "2024-01-08T08:00:00")
    private LocalDateTime dateDebut;

    @Schema(description = "Planned end date", example = "2024-02-02T17:00:00")
    private LocalDateTime dateFin;

    @Schema(description = "Owning project identifier")
    private UUID projectId;
}

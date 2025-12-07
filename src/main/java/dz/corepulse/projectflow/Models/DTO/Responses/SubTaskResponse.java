package dz.corepulse.projectflow.Models.DTO.Responses;

import dz.corepulse.projectflow.Models.Enums.SubTaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Subtask representation returned by the API.")
public class SubTaskResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Subtask title")
    private String name;

    @Schema(description = "Subtask description")
    private String description;

    @Schema(description = "Current status")
    private SubTaskStatus statut;

    @Schema(description = "Parent task identifier")
    private UUID taskId;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}

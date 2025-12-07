package dz.corepulse.projectflow.Models.DTO.Requests;

import dz.corepulse.projectflow.Models.Enums.SubTaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Payload for creating or updating a subtask.")
public class SubTaskRequest {

    @Schema(description = "Subtask title", example = "Wire login layout")
    private String name;

    @Schema(description = "Detailed description", example = "Create low fidelity wireframe")
    private String description;

    @Schema(description = "Current status", example = "TODO")
    private SubTaskStatus statut;

    @Schema(description = "Parent task identifier")
    private UUID taskId;
}

package dz.corepulse.projectflow.Models.DTO.Responses;

import dz.corepulse.projectflow.Models.Enums.SubTaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubTaskResponse {

    private UUID id;

    private String name;
    private String description;
    private SubTaskStatus statut;

    private UUID taskId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

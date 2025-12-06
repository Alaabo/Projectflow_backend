package dz.corepulse.projectflow.Models.DTO.Responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubTaskResponse {

    private UUID id;

    private String subTaskName;
    private String desc;
    private String statut;
    private String priority;

    private LocalDateTime dateDebut;
    private Integer timeLeft;
    private Integer timeEstimate;

    private UUID taskId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


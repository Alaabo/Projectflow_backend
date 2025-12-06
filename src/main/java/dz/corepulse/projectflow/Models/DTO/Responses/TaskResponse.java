package dz.corepulse.projectflow.Models.DTO.Responses;

import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskResponse {

    private UUID id;

    private String taskName;
    private String desc;
    private TaskStatus statut;
    private String priority;

    private LocalDateTime dateDebut;
    private Integer timeLeft;
    private Integer timeEstimate;

    private String tag;

    private UUID storyId;
    private UUID sprintId;
    private UUID assignedUserId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

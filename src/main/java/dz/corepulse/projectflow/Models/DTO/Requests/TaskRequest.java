package dz.corepulse.projectflow.Models.DTO.Requests;

import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskRequest {

    private String taskName;
    private String description;
    private TaskStatus statut;
    private String priority;

    private LocalDateTime dateDebut;
    private Integer timeLeft;
    private Integer timeEstimate;

    private String tag;

    private UUID storyId;
    private UUID sprintId; // optional
    private UUID assignedUserId;
}

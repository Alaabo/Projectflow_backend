package dz.corepulse.projectflow.Models.DTO.Filters;

import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskFilter {

    private TaskStatus statut;
    private String priority;
    private UUID storyId;
    private UUID sprintId;
}

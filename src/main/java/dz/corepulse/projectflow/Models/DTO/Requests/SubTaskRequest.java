package dz.corepulse.projectflow.Models.DTO.Requests;

import dz.corepulse.projectflow.Models.Enums.SubTaskStatus;
import lombok.Data;
import java.util.UUID;

@Data
public class SubTaskRequest {

    private String name;
    private String description;
    private SubTaskStatus statut;

    private UUID taskId;
}

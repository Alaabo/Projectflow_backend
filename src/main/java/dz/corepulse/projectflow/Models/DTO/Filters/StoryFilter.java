package dz.corepulse.projectflow.Models.DTO.Filters;

import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class StoryFilter {

    private StoryStatus statut;
    private String priority;
    private UUID sprintId;
}

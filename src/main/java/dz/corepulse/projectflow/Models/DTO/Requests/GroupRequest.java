package dz.corepulse.projectflow.Models.DTO.Requests;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GroupRequest {
    private String name;

    // foreign keys
    private UUID projectId;

    // relation: users inside this group
    private List<UUID> userIds;
}

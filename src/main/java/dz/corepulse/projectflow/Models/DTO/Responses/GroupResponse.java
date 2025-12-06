package dz.corepulse.projectflow.Models.DTO.Responses;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class GroupResponse {
    private UUID id;
    private String name;

    private UUID projectId;
    private List<UUID> userIds;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

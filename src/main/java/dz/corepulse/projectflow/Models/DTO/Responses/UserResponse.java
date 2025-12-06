package dz.corepulse.projectflow.Models.DTO.Responses;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String name;

    private List<UUID> profileIds;
    private List<UUID> groupIds;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

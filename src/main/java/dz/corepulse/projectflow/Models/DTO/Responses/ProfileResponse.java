package dz.corepulse.projectflow.Models.DTO.Responses;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

@Data
public class ProfileResponse {
    private UUID id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

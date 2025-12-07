package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "User representation returned by the API.")
public class UserResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Full name")
    private String name;

    @Schema(description = "Identifiers of assigned profiles")
    private List<UUID> profileIds;

    @Schema(description = "Identifiers of groups the user belongs to")
    private List<UUID> groupIds;

    @Schema(description = "Identifiers of projects the user participates in")
    private List<UUID> projectIds;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}

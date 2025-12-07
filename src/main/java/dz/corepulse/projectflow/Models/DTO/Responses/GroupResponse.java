package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Group resource returned by the API.")
public class GroupResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Group name")
    private String name;

    @Schema(description = "Owning project identifier")
    private UUID projectId;

    @Schema(description = "Identifiers of users inside the group")
    private List<UUID> userIds;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}

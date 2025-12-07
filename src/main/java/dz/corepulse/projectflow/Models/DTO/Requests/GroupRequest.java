package dz.corepulse.projectflow.Models.DTO.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Payload for creating or updating a project group.")
public class GroupRequest {

    @Schema(description = "Group name", example = "Backend Team")
    private String name;

    @Schema(description = "Project identifier the group belongs to")
    private UUID projectId;

    @Schema(description = "User identifiers that belong to the group")
    private List<UUID> userIds;
}

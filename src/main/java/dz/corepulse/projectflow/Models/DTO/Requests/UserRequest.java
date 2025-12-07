package dz.corepulse.projectflow.Models.DTO.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Schema(description = "Payload used to create or update a user.")
public class UserRequest {

    @Schema(description = "Full name", example = "Alice Labib")
    private String name;

    @Schema(description = "Identifiers of profiles to assign")
    private List<UUID> profileIds;

    @Schema(description = "Identifiers of project groups this user belongs to")
    private List<UUID> groupIds;
}

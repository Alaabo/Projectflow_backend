package dz.corepulse.projectflow.Models.DTO.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Payload for creating or updating a privilege entry.")
public class PrivilegeRequest {

    @Schema(description = "Privilege label", example = "PROJECT_VIEW")
    private String name;

    @Schema(description = "Detailed privilege description", example = "Allows seeing project dashboards")
    private String privilege;
}

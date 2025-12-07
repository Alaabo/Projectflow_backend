package dz.corepulse.projectflow.Models.DTO.Requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Payload for creating or renaming a profile.")
public class ProfileRequest {

    @Schema(description = "Profile name", example = "Project Manager")
    private String name;
}

package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Privilege definition returned by the API.")
public class PrivilegeResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Privilege name")
    private String name;

    @Schema(description = "Detailed privilege description")
    private String privilege;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}

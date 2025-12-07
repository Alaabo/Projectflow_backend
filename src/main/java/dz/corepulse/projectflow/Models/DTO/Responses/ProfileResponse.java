package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Schema(description = "Profile representation returned by the API.")
public class ProfileResponse {

    @Schema(description = "Unique identifier")
    private UUID id;

    @Schema(description = "Profile name")
    private String name;

    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;

    @Schema(description = "Last update timestamp")
    private LocalDateTime updatedAt;
}

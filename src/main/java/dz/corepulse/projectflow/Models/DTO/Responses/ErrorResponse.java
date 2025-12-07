package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(description = "Error payload returned when a request fails.")
public record ErrorResponse(
        @Schema(description = "HTTP status code") int status,
        @Schema(description = "Short error label") String error,
        @Schema(description = "Detailed message") String message,
        @Schema(description = "Endpoint path") String path,
        @Schema(description = "Timestamp of the error") LocalDateTime timestamp
) {
    public static ErrorResponse of(HttpStatus status, String message, String path) {
        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                LocalDateTime.now()
        );
    }
}

package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Standard response envelope wrapping every API call.")
public record ApiResponse<T>(
        @Schema(description = "Whether the call completed successfully") boolean success,
        @Schema(description = "Payload returned by the endpoint") T data,
        @Schema(description = "Optional human readable context") String message,
        @Schema(description = "Timestamp when the response was generated") LocalDateTime timestamp
) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, data, message, LocalDateTime.now());
    }

    public static ApiResponse<Void> message(String message) {
        return new ApiResponse<>(true, null, message, LocalDateTime.now());
    }
}

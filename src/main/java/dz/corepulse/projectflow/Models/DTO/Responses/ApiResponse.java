package dz.corepulse.projectflow.Models.DTO.Responses;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        boolean success,
        T data,
        String message,
        LocalDateTime timestamp
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

package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = "Wrapper for paginated responses.")
public record PageResponse<T>(
        @Schema(description = "Content of the current page") List<T> content,
        @Schema(description = "Current page index (0 based)") int page,
        @Schema(description = "Size of the page requested") int size,
        @Schema(description = "Total number of elements across all pages") long totalElements,
        @Schema(description = "Total number of pages available") int totalPages
) {
    public static <T> PageResponse<T> from(Page<T> page) {
        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}

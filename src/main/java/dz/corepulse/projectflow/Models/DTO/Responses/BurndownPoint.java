package dz.corepulse.projectflow.Models.DTO.Responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Point plotted on a burndown chart.")
public record BurndownPoint(
        @Schema(description = "Calendar date of the data point") LocalDate date,
        @Schema(description = "Remaining points for the day") int remainingPoints
) {
}

package dz.corepulse.projectflow.Models.DTO.Responses;

import java.time.LocalDate;

public record BurndownPoint(LocalDate date, int remainingPoints) {
}

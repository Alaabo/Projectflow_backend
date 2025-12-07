package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.BurndownPoint;
import dz.corepulse.projectflow.Services.SprintAnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
@Tag(name = "Analytics", description = "Sprint and project progress metrics")
public class AnalyticsController {

    private final SprintAnalyticsService analyticsService;

    @GetMapping("/sprint/{id}/burndown")
    @Operation(summary = "Get sprint burndown", description = "Returns remaining effort by day for the sprint.")
    public ApiResponse<List<BurndownPoint>> burndown(@PathVariable UUID id) {
        return ApiResponse.success(analyticsService.getBurndown(id));
    }

    @GetMapping("/project/{id}/velocity")
    @Operation(summary = "Compute project velocity", description = "Calculates the average completed points across the last N sprints.")
    public ApiResponse<Map<String, Integer>> velocity(@PathVariable UUID id,
                                                      @RequestParam(name = "last", defaultValue = "3") int last) {
        return ApiResponse.success(Map.of("velocity", analyticsService.computeVelocity(id, last)));
    }

    @GetMapping("/sprint/{id}/progress")
    @Operation(summary = "Compute sprint progress", description = "Returns aggregate completion percentage for a sprint.")
    public ApiResponse<Map<String, Integer>> progress(@PathVariable UUID id) {
        return ApiResponse.success(Map.of("progress", analyticsService.computeSprintProgress(id)));
    }
}

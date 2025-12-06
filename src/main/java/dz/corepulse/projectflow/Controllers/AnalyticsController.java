package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.BurndownPoint;
import dz.corepulse.projectflow.Services.SprintAnalyticsService;
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
public class AnalyticsController {

    private final SprintAnalyticsService analyticsService;

    @GetMapping("/sprint/{id}/burndown")
    public ApiResponse<List<BurndownPoint>> burndown(@PathVariable UUID id) {
        return ApiResponse.success(analyticsService.getBurndown(id));
    }

    @GetMapping("/project/{id}/velocity")
    public ApiResponse<Map<String, Integer>> velocity(@PathVariable UUID id,
                                                      @RequestParam(name = "last", defaultValue = "3") int last) {
        return ApiResponse.success(Map.of("velocity", analyticsService.computeVelocity(id, last)));
    }

    @GetMapping("/sprint/{id}/progress")
    public ApiResponse<Map<String, Integer>> progress(@PathVariable UUID id) {
        return ApiResponse.success(Map.of("progress", analyticsService.computeSprintProgress(id)));
    }
}

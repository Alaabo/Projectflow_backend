package dz.corepulse.projectflow.Controllers;
import dz.corepulse.projectflow.Models.DTO.Filters.SprintFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.SprintRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.SprintResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Services.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final SprintService service;

    @PostMapping
    public ApiResponse<SprintResponse> create(@RequestBody SprintRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<SprintResponse> update(@PathVariable UUID id, @RequestBody SprintRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<SprintResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<SprintResponse>> getAll(@ModelAttribute SprintFilter filter,
                                                            Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Sprint deleted successfully");
    }

    @PostMapping("/{sprintId}/tasks/{taskId}")
    public ApiResponse<TaskResponse> addTaskToSprint(@PathVariable UUID sprintId,
                                                     @PathVariable UUID taskId) {
        return ApiResponse.success(service.addTaskToSprint(sprintId, taskId));
    }

    @PostMapping("/{fromSprintId}/move-story/{storyId}/to/{toSprintId}")
    public ApiResponse<StoryResponse> moveStoryBetweenSprints(@PathVariable UUID fromSprintId,
                                                              @PathVariable UUID storyId,
                                                              @PathVariable UUID toSprintId) {
        return ApiResponse.success(service.moveStory(fromSprintId, storyId, toSprintId));
    }
}

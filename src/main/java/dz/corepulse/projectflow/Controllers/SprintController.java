package dz.corepulse.projectflow.Controllers;
import dz.corepulse.projectflow.Models.DTO.Filters.SprintFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.SprintRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.SprintResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Services.SprintService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sprints")
@RequiredArgsConstructor
@Tag(name = "Sprints", description = "Sprint planning and scheduling")
public class SprintController {

    private final SprintService service;

    @PostMapping
    @Operation(summary = "Create sprint", description = "Creates a sprint for a project timeline.")
    public ApiResponse<SprintResponse> create(@RequestBody SprintRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update sprint", description = "Updates sprint metadata by identifier.")
    public ApiResponse<SprintResponse> update(@PathVariable UUID id, @RequestBody SprintRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get sprint", description = "Fetches sprint details.")
    public ApiResponse<SprintResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List sprints", description = "Returns paginated sprints filtered by status and project.")
    public ApiResponse<PageResponse<SprintResponse>> getAll(@ModelAttribute SprintFilter filter,
                                                            Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sprint", description = "Deletes the sprint and releases associated tasks.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Sprint deleted successfully");
    }

    @PostMapping("/{sprintId}/tasks/{taskId}")
    @Operation(summary = "Attach task to sprint", description = "Links a task to a sprint backlog.")
    public ApiResponse<TaskResponse> addTaskToSprint(@PathVariable UUID sprintId,
                                                     @PathVariable UUID taskId) {
        return ApiResponse.success(service.addTaskToSprint(sprintId, taskId));
    }

    @PostMapping("/{fromSprintId}/move-story/{storyId}/to/{toSprintId}")
    @Operation(summary = "Move story between sprints", description = "Moves a story from one sprint backlog to another.")
    public ApiResponse<StoryResponse> moveStoryBetweenSprints(@PathVariable UUID fromSprintId,
                                                              @PathVariable UUID storyId,
                                                              @PathVariable UUID toSprintId) {
        return ApiResponse.success(service.moveStory(fromSprintId, storyId, toSprintId));
    }
}

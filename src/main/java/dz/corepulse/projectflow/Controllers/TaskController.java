package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Filters.TaskFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.TaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Services.TaskService;
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
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Sprint tasks managed under stories")
public class TaskController {

    private final TaskService service;

    @PostMapping
    @Operation(summary = "Create task", description = "Creates a task linked to a story (and optionally a sprint).")
    public ApiResponse<TaskResponse> create(@RequestBody TaskRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task", description = "Updates attributes of a task.")
    public ApiResponse<TaskResponse> update(@PathVariable UUID id, @RequestBody TaskRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task", description = "Fetches a task by identifier.")
    public ApiResponse<TaskResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List tasks", description = "Returns paginated tasks matching filter criteria.")
    public ApiResponse<PageResponse<TaskResponse>> getAll(@ModelAttribute TaskFilter filter,
                                                          Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task", description = "Deletes a task and its subtasks.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Task deleted successfully");
    }
}

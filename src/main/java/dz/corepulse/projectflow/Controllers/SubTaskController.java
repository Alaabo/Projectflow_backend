package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Requests.SubTaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.SubTaskResponse;
import dz.corepulse.projectflow.Services.SubTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subtasks")
@RequiredArgsConstructor
@Tag(name = "Subtasks", description = "Atomic work items linked to tasks")
public class SubTaskController {

    private final SubTaskService service;

    @PostMapping
    @Operation(summary = "Create subtask", description = "Creates a subtask attached to a parent task.")
    public ApiResponse<SubTaskResponse> create(@RequestBody SubTaskRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update subtask", description = "Updates name or status of a subtask.")
    public ApiResponse<SubTaskResponse> update(@PathVariable UUID id, @RequestBody SubTaskRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get subtask", description = "Retrieves a subtask by identifier.")
    public ApiResponse<SubTaskResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List subtasks", description = "Returns all subtasks in the system.")
    public ApiResponse<List<SubTaskResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete subtask", description = "Deletes a subtask.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Subtask deleted successfully");
    }
}

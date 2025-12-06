package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Filters.TaskFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.TaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @PostMapping
    public ApiResponse<TaskResponse> create(@RequestBody TaskRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<TaskResponse> update(@PathVariable UUID id, @RequestBody TaskRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<TaskResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<TaskResponse>> getAll(@ModelAttribute TaskFilter filter,
                                                          Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Task deleted successfully");
    }
}

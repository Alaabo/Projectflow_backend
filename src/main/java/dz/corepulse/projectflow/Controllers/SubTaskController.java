package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Requests.SubTaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.SubTaskResponse;
import dz.corepulse.projectflow.Services.SubTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subtasks")
@RequiredArgsConstructor
public class SubTaskController {

    private final SubTaskService service;

    @PostMapping
    public ApiResponse<SubTaskResponse> create(@RequestBody SubTaskRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<SubTaskResponse> update(@PathVariable UUID id, @RequestBody SubTaskRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<SubTaskResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<List<SubTaskResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Subtask deleted successfully");
    }
}

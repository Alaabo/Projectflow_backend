package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Filters.ProjectFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.ProjectRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.ProjectResponse;
import dz.corepulse.projectflow.Services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    public ApiResponse<ProjectResponse> create(@RequestBody ProjectRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProjectResponse> update(@PathVariable UUID id,
                                               @RequestBody ProjectRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProjectResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<ProjectResponse>> getAll(@ModelAttribute ProjectFilter filter,
                                                             Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Project deleted successfully");
    }

    @PostMapping("/{projectId}/users/{userId}")
    public ApiResponse<ProjectResponse> addUserToProject(@PathVariable UUID projectId,
                                                         @PathVariable UUID userId) {
        return ApiResponse.success(service.addUser(projectId, userId));
    }
}

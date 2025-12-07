package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Filters.ProjectFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.ProjectRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.ProjectResponse;
import dz.corepulse.projectflow.Services.ProjectService;
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
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@Tag(name = "Projects", description = "Manage projects and team membership")
public class ProjectController {

    private final ProjectService service;

    @PostMapping
    @Operation(summary = "Create project", description = "Creates a new project with optional members.")
    public ApiResponse<ProjectResponse> create(@RequestBody ProjectRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update project", description = "Updates an existing project using its identifier.")
    public ApiResponse<ProjectResponse> update(@PathVariable UUID id,
                                               @RequestBody ProjectRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project", description = "Fetches a project by its identifier.")
    public ApiResponse<ProjectResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List projects", description = "Returns paginated projects matching the supplied filters.")
    public ApiResponse<PageResponse<ProjectResponse>> getAll(@ModelAttribute ProjectFilter filter,
                                                             Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete project", description = "Removes a project and detaches related entities.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Project deleted successfully");
    }

    @PostMapping("/{projectId}/users/{userId}")
    @Operation(summary = "Add user to project", description = "Assigns an existing user to the specified project.")
    public ApiResponse<ProjectResponse> addUserToProject(@PathVariable UUID projectId,
                                                         @PathVariable UUID userId) {
        return ApiResponse.success(service.addUser(projectId, userId));
    }
}

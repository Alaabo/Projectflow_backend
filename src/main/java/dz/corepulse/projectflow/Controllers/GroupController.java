package dz.corepulse.projectflow.Controllers;
import dz.corepulse.projectflow.Models.DTO.Requests.GroupRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.GroupResponse;
import dz.corepulse.projectflow.Services.GroupService;
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
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
@Tag(name = "Groups", description = "Project groups and memberships")
public class GroupController {

    private final GroupService service;

    @PostMapping
    @Operation(summary = "Create group", description = "Creates a group with optional project association.")
    public ApiResponse<GroupResponse> create(@RequestBody GroupRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update group", description = "Updates name or project binding for a group.")
    public ApiResponse<GroupResponse> update(@PathVariable UUID id,
                                             @RequestBody GroupRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get group", description = "Retrieves a single group definition.")
    public ApiResponse<GroupResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List groups", description = "Returns all available groups.")
    public ApiResponse<List<GroupResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete group", description = "Removes a group and associated assignments.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Group deleted successfully");
    }
}

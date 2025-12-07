package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Requests.PrivilegeRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PrivilegeResponse;
import dz.corepulse.projectflow.Services.PrivilegeService;
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
@RequestMapping("/api/v1/privileges")
@RequiredArgsConstructor
@Tag(name = "Privileges", description = "Manage privileges and their metadata")
public class PrivilegeController {

    private final PrivilegeService service;

    @PostMapping
    @Operation(summary = "Create privilege", description = "Registers a new privilege entry.")
    public ApiResponse<PrivilegeResponse> create(@RequestBody PrivilegeRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update privilege", description = "Updates scope or name of an existing privilege.")
    public ApiResponse<PrivilegeResponse> update(@PathVariable UUID id,
                                                 @RequestBody PrivilegeRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get privilege", description = "Fetches privilege details by identifier.")
    public ApiResponse<PrivilegeResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List privileges", description = "Returns all configured privileges.")
    public ApiResponse<List<PrivilegeResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete privilege", description = "Deletes a privilege definition.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Privilege deleted successfully");
    }
}

package dz.corepulse.projectflow.Controllers;
import dz.corepulse.projectflow.Models.DTO.Requests.UserRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.UserResponse;
import dz.corepulse.projectflow.Services.UserService;
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
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Workspace user directory")
public class UserController {

    private final UserService service;

    @PostMapping
    @Operation(summary = "Create user", description = "Registers a new user.")
    public ApiResponse<UserResponse> create(@RequestBody UserRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Updates a user's profile information.")
    public ApiResponse<UserResponse> update(@PathVariable UUID id,
                                            @RequestBody UserRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user", description = "Fetches a user by identifier.")
    public ApiResponse<UserResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List users", description = "Returns all users.")
    public ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes a user from the workspace.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("User deleted successfully");
    }
}

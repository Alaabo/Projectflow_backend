package dz.corepulse.projectflow.Controllers;
import dz.corepulse.projectflow.Models.DTO.Requests.ProfileRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.ProfileResponse;
import dz.corepulse.projectflow.Services.ProfileService;
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
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
@Tag(name = "Profiles", description = "Role profiles composed of privileges")
public class ProfileController {

    private final ProfileService service;

    @PostMapping
    @Operation(summary = "Create profile", description = "Creates a profile with assigned privileges.")
    public ApiResponse<ProfileResponse> create(@RequestBody ProfileRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update profile", description = "Updates a profile's name or privileges.")
    public ApiResponse<ProfileResponse> update(
            @PathVariable UUID id,
            @RequestBody ProfileRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get profile", description = "Gets profile details by identifier.")
    public ApiResponse<ProfileResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List profiles", description = "Returns all available profiles.")
    public ApiResponse<List<ProfileResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete profile", description = "Deletes a profile and removes its privilege associations.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Profile deleted successfully");
    }
}

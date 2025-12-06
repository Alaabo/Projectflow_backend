package dz.corepulse.projectflow.Controllers;
import dz.corepulse.projectflow.Models.DTO.Requests.ProfileRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.ProfileResponse;
import dz.corepulse.projectflow.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @PostMapping
    public ApiResponse<ProfileResponse> create(@RequestBody ProfileRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProfileResponse> update(
            @PathVariable UUID id,
            @RequestBody ProfileRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProfileResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<List<ProfileResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Profile deleted successfully");
    }
}

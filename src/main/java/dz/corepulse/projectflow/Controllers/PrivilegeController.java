package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Requests.PrivilegeRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PrivilegeResponse;
import dz.corepulse.projectflow.Services.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/privileges")
@RequiredArgsConstructor
public class PrivilegeController {

    private final PrivilegeService service;

    @PostMapping
    public ApiResponse<PrivilegeResponse> create(@RequestBody PrivilegeRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<PrivilegeResponse> update(@PathVariable UUID id,
                                                 @RequestBody PrivilegeRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<PrivilegeResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<List<PrivilegeResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Privilege deleted successfully");
    }
}

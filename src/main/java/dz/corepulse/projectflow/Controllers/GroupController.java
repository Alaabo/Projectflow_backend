package dz.corepulse.projectflow.Controllers;
import dz.corepulse.projectflow.Models.DTO.Requests.GroupRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.GroupResponse;
import dz.corepulse.projectflow.Services.GroupService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService service;

    @PostMapping
    public ApiResponse<GroupResponse> create(@RequestBody GroupRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<GroupResponse> update(@PathVariable UUID id,
                                             @RequestBody GroupRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<GroupResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<List<GroupResponse>> getAll() {
        return ApiResponse.success(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Group deleted successfully");
    }
}

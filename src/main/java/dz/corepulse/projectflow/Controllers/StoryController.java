package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Filters.StoryFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.StoryRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Services.StoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stories")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService service;

    @PostMapping
    public ApiResponse<StoryResponse> create(@RequestBody StoryRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<StoryResponse> update(@PathVariable UUID id, @RequestBody StoryRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<StoryResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<StoryResponse>> getAll(@ModelAttribute StoryFilter filter,
                                                           Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Story deleted successfully");
    }
}

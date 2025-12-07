package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Filters.StoryFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.StoryRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Services.StoryService;
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
@RequestMapping("/api/v1/stories")
@RequiredArgsConstructor
@Tag(name = "Stories", description = "User stories scoped under epics or sprints")
public class StoryController {

    private final StoryService service;

    @PostMapping
    @Operation(summary = "Create story", description = "Creates a new story and associates it to an epic and sprint.")
    public ApiResponse<StoryResponse> create(@RequestBody StoryRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update story", description = "Updates story details by identifier.")
    public ApiResponse<StoryResponse> update(@PathVariable UUID id, @RequestBody StoryRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get story", description = "Fetches story metadata.")
    public ApiResponse<StoryResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List stories", description = "Returns paginated stories filtered by project context.")
    public ApiResponse<PageResponse<StoryResponse>> getAll(@ModelAttribute StoryFilter filter,
                                                           Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete story", description = "Deletes the story and its tasks.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Story deleted successfully");
    }
}

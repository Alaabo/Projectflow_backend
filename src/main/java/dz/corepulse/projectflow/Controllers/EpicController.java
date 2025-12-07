package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Filters.EpicFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.EpicRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.EpicResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Services.EpicService;
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
@RequestMapping("/api/v1/epics")
@RequiredArgsConstructor
@Tag(name = "Epics", description = "Large bodies of work that group stories")
public class EpicController {

    private final EpicService service;

    @PostMapping
    @Operation(summary = "Create epic", description = "Creates a new epic under an existing project.")
    public ApiResponse<EpicResponse> create(@RequestBody EpicRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update epic", description = "Updates the epic fields identified by ID.")
    public ApiResponse<EpicResponse> update(@PathVariable UUID id, @RequestBody EpicRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get epic", description = "Fetches an epic with its metadata.")
    public ApiResponse<EpicResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    @Operation(summary = "List epics", description = "Returns paginated epics filtered by project, status, or other criteria.")
    public ApiResponse<PageResponse<EpicResponse>> getAll(@ModelAttribute EpicFilter filter,
                                                          Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete epic", description = "Deletes the epic and cascades to related stories.")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Epic deleted successfully");
    }
}

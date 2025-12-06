package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Filters.EpicFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.EpicRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.EpicResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Services.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/epics")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService service;

    @PostMapping
    public ApiResponse<EpicResponse> create(@RequestBody EpicRequest request) {
        return ApiResponse.success(service.create(request));
    }

    @PutMapping("/{id}")
    public ApiResponse<EpicResponse> update(@PathVariable UUID id, @RequestBody EpicRequest request) {
        return ApiResponse.success(service.update(id, request));
    }

    @GetMapping("/{id}")
    public ApiResponse<EpicResponse> get(@PathVariable UUID id) {
        return ApiResponse.success(service.get(id));
    }

    @GetMapping
    public ApiResponse<PageResponse<EpicResponse>> getAll(@ModelAttribute EpicFilter filter,
                                                          Pageable pageable) {
        return ApiResponse.success(service.getAll(filter, pageable));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.message("Epic deleted successfully");
    }
}

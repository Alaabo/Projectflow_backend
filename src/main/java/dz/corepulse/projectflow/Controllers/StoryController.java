package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Requests.StoryRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Services.StoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stories")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService service;

    @PostMapping
    public StoryResponse create(@RequestBody StoryRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public StoryResponse update(@PathVariable UUID id, @RequestBody StoryRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public StoryResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<StoryResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}


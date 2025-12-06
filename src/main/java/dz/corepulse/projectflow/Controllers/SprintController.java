package dz.corepulse.projectflow.Controllers;


import dz.corepulse.projectflow.Models.DTO.Requests.SprintRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SprintResponse;
import dz.corepulse.projectflow.Services.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sprints")
@RequiredArgsConstructor
public class SprintController {

    private final SprintService service;

    @PostMapping
    public SprintResponse create(@RequestBody SprintRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public SprintResponse update(@PathVariable UUID id, @RequestBody SprintRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public SprintResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<SprintResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}


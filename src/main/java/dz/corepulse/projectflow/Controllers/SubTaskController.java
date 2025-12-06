package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Requests.SubTaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SubTaskResponse;
import dz.corepulse.projectflow.Services.SubTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/subtasks")
@RequiredArgsConstructor
public class SubTaskController {

    private final SubTaskService service;

    @PostMapping
    public SubTaskResponse create(@RequestBody SubTaskRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public SubTaskResponse update(@PathVariable UUID id, @RequestBody SubTaskRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public SubTaskResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<SubTaskResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}


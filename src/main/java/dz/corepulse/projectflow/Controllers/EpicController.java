package dz.corepulse.projectflow.Controllers;



import dz.corepulse.projectflow.Models.DTO.Requests.EpicRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.EpicResponse;
import dz.corepulse.projectflow.Services.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/epics")
@RequiredArgsConstructor
public class EpicController {

    private final EpicService service;

    @PostMapping
    public EpicResponse create(@RequestBody EpicRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public EpicResponse update(@PathVariable UUID id, @RequestBody EpicRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public EpicResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<EpicResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}


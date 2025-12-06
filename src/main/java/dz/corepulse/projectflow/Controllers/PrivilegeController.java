package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Requests.PrivilegeRequest;
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
    public PrivilegeResponse create(@RequestBody PrivilegeRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public PrivilegeResponse update(@PathVariable UUID id,
                                    @RequestBody PrivilegeRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public PrivilegeResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<PrivilegeResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
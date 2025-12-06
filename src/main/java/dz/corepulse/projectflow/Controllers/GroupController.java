package dz.corepulse.projectflow.Controllers;



import dz.corepulse.projectflow.Models.DTO.Requests.GroupRequest;
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
    public GroupResponse create(@RequestBody GroupRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public GroupResponse update(@PathVariable UUID id,
                                @RequestBody GroupRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public GroupResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<GroupResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}


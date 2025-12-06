package dz.corepulse.projectflow.Controllers;





import dz.corepulse.projectflow.Models.DTO.Requests.ProfileRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ProfileResponse;
import dz.corepulse.projectflow.Services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @PostMapping
    public ProfileResponse create(@RequestBody ProfileRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ProfileResponse update(
            @PathVariable UUID id,
            @RequestBody ProfileRequest request) {
        return service.update(id, request);
    }

    @GetMapping("/{id}")
    public ProfileResponse get(@PathVariable UUID id) {
        return service.get(id);
    }

    @GetMapping
    public List<ProfileResponse> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}


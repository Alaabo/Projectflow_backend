package dz.corepulse.projectflow.Services;



import dz.corepulse.projectflow.Models.DTO.Requests.SprintRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SprintResponse;

import java.util.List;
import java.util.UUID;

public interface SprintService {

    SprintResponse create(SprintRequest request);

    SprintResponse update(UUID id, SprintRequest request);

    SprintResponse get(UUID id);

    List<SprintResponse> getAll();

    void delete(UUID id);
}


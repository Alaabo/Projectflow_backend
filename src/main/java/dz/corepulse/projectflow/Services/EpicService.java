package dz.corepulse.projectflow.Services;



import dz.corepulse.projectflow.Models.DTO.Requests.EpicRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.EpicResponse;

import java.util.List;
import java.util.UUID;

public interface EpicService {

    EpicResponse create(EpicRequest request);

    EpicResponse update(UUID id, EpicRequest request);

    EpicResponse get(UUID id);

    List<EpicResponse> getAll();

    void delete(UUID id);
}


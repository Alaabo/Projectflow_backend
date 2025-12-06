package dz.corepulse.projectflow.Services;

import dz.corepulse.projectflow.Models.DTO.Requests.StoryRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;

import java.util.List;
import java.util.UUID;

public interface StoryService {

    StoryResponse create(StoryRequest request);

    StoryResponse update(UUID id, StoryRequest request);

    StoryResponse get(UUID id);

    List<StoryResponse> getAll();

    void delete(UUID id);
}

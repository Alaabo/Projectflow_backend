package dz.corepulse.projectflow.Services;

import dz.corepulse.projectflow.Models.DTO.Filters.StoryFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.StoryRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StoryService {

    StoryResponse create(StoryRequest request);

    StoryResponse update(UUID id, StoryRequest request);

    StoryResponse get(UUID id);

    PageResponse<StoryResponse> getAll(StoryFilter filter, Pageable pageable);

    void delete(UUID id);
}

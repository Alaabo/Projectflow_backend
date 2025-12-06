package dz.corepulse.projectflow.Services;



import dz.corepulse.projectflow.Models.DTO.Filters.EpicFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.EpicRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.EpicResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EpicService {

    EpicResponse create(EpicRequest request);

    EpicResponse update(UUID id, EpicRequest request);

    EpicResponse get(UUID id);

    PageResponse<EpicResponse> getAll(EpicFilter filter, Pageable pageable);

    void delete(UUID id);
}

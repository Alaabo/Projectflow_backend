package dz.corepulse.projectflow.Services;



import dz.corepulse.projectflow.Models.DTO.Filters.ProjectFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.ProjectRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.ProjectResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProjectService {

    ProjectResponse create(ProjectRequest request);

    ProjectResponse update(UUID id, ProjectRequest request);

    ProjectResponse get(UUID id);

    PageResponse<ProjectResponse> getAll(ProjectFilter filter, Pageable pageable);

    void delete(UUID id);

    ProjectResponse addUser(UUID projectId, UUID userId);
}

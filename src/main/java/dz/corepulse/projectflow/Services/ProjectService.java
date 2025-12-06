package dz.corepulse.projectflow.Services;



import dz.corepulse.projectflow.Models.DTO.Requests.ProjectRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ProjectResponse;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    ProjectResponse create(ProjectRequest request);

    ProjectResponse update(UUID id, ProjectRequest request);

    ProjectResponse get(UUID id);

    List<ProjectResponse> getAll();

    void delete(UUID id);
}


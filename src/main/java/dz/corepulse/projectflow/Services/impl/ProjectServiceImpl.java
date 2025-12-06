package dz.corepulse.projectflow.Services.impl;



import dz.corepulse.projectflow.Mappers.ProjectMapper;
import dz.corepulse.projectflow.Models.DTO.Requests.ProjectRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.ProjectResponse;
import dz.corepulse.projectflow.Models.Entities.Project;
import dz.corepulse.projectflow.Repositories.ProjectRepository;
import dz.corepulse.projectflow.Services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    @Override
    public ProjectResponse create(ProjectRequest request) {
        Project entity = mapper.toEntity(request);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public ProjectResponse update(UUID id, ProjectRequest request) {
        Project entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        mapper.updateEntity(request, entity);
        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Override
    public ProjectResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public List<ProjectResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Project not found");

        repository.deleteById(id);
    }
}


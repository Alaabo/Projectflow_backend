package dz.corepulse.projectflow.Services.impl;


import dz.corepulse.projectflow.Mappers.SprintMapper;
import dz.corepulse.projectflow.Models.DTO.Requests.SprintRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SprintResponse;
import dz.corepulse.projectflow.Models.Entities.Project;
import dz.corepulse.projectflow.Models.Entities.Sprint;
import dz.corepulse.projectflow.Repositories.ProjectRepository;
import dz.corepulse.projectflow.Repositories.SprintRepository;
import dz.corepulse.projectflow.Services.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository repository;
    private final ProjectRepository projectRepository;
    private final SprintMapper mapper;

    @Override
    public SprintResponse create(SprintRequest request) {
        Sprint entity = mapper.toEntity(request);

        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            entity.setProject(project);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public SprintResponse update(UUID id, SprintRequest request) {
        Sprint entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));

        mapper.updateEntity(request, entity);

        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Project not found"));
            entity.setProject(project);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public SprintResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));
    }

    @Override
    public List<SprintResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Sprint not found");

        repository.deleteById(id);
    }
}


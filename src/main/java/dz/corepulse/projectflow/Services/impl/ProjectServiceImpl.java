package dz.corepulse.projectflow.Services.impl;



import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.ProjectMapper;
import dz.corepulse.projectflow.Models.DTO.Filters.ProjectFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.ProjectRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.ProjectResponse;
import dz.corepulse.projectflow.Models.Entities.Project;
import dz.corepulse.projectflow.Repositories.ProjectRepository;
import dz.corepulse.projectflow.Repositories.Specifications.ProjectSpecifications;
import dz.corepulse.projectflow.Repositories.UserRepository;
import dz.corepulse.projectflow.Services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final UserRepository userRepository;
    private final ProjectMapper mapper;

    @Override
    public ProjectResponse create(ProjectRequest request) {
        Project entity = mapper.toEntity(request);
        if (request.getUserIds() != null) {
            entity.setUserList(userRepository.findAllById(request.getUserIds()));
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public ProjectResponse update(UUID id, ProjectRequest request) {
        Project entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Project not found"));

        mapper.updateEntity(request, entity);

        if (request.getUserIds() != null) {
            entity.setUserList(userRepository.findAllById(request.getUserIds()));
        }
        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Override
    public ProjectResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Project not found"));
    }

    @Override
    public PageResponse<ProjectResponse> getAll(ProjectFilter filter, Pageable pageable) {
        var page = repository.findAll(ProjectSpecifications.withFilter(filter), pageable)
                .map(mapper::toDto);
        return PageResponse.from(page);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("Project not found");

        repository.deleteById(id);
    }

    @Override
    public ProjectResponse addUser(UUID projectId, UUID userId) {
        Project project = repository.findById(projectId)
                .orElseThrow(() -> new BusinessException("Project not found"));
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));

        boolean alreadyAssigned = project.getUserList().stream()
                .anyMatch(existing -> existing.getId().equals(userId));
        if (!alreadyAssigned) {
            project.getUserList().add(user);
        }

        repository.save(project);
        return mapper.toDto(project);
    }
}

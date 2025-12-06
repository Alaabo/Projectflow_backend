package dz.corepulse.projectflow.Services.impl;


import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.EpicMapper;
import dz.corepulse.projectflow.Models.DTO.Filters.EpicFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.EpicRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.EpicResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.Entities.Epic;
import dz.corepulse.projectflow.Models.Entities.Project;
import dz.corepulse.projectflow.Repositories.EpicRepository;
import dz.corepulse.projectflow.Repositories.ProjectRepository;
import dz.corepulse.projectflow.Repositories.Specifications.EpicSpecifications;
import dz.corepulse.projectflow.Services.EpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {

    private final EpicRepository repository;
    private final ProjectRepository projectRepository;
    private final EpicMapper mapper;

    @Override
    public EpicResponse create(EpicRequest request) {
        Epic entity = mapper.toEntity(request);

        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new BusinessException("Project not found"));
            entity.setProject(project);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public EpicResponse update(UUID id, EpicRequest request) {
        Epic entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Epic not found"));

        mapper.updateEntity(request, entity);

        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new BusinessException("Project not found"));
            entity.setProject(project);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public EpicResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Epic not found"));
    }

    @Override
    public PageResponse<EpicResponse> getAll(EpicFilter filter, Pageable pageable) {
        var page = repository.findAll(EpicSpecifications.withFilter(filter), pageable)
                .map(mapper::toDto);
        return PageResponse.from(page);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("Epic not found");

        repository.deleteById(id);
    }
}

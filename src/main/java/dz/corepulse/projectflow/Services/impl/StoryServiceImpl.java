package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Models.DTO.Filters.StoryFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.StoryRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Models.Entities.Epic;
import dz.corepulse.projectflow.Models.Entities.Sprint;
import dz.corepulse.projectflow.Models.Entities.Story;
import dz.corepulse.projectflow.Repositories.EpicRepository;
import dz.corepulse.projectflow.Repositories.SprintRepository;
import dz.corepulse.projectflow.Repositories.StoryRepository;
import dz.corepulse.projectflow.Repositories.Specifications.StorySpecifications;
import dz.corepulse.projectflow.Mappers.StoryMapper;
import dz.corepulse.projectflow.Services.StoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {

    private final StoryRepository repository;
    private final EpicRepository epicRepository;
    private final SprintRepository sprintRepository;
    private final StoryMapper mapper;

    @Override
    public StoryResponse create(StoryRequest request) {
        Story entity = mapper.toEntity(request);

        if (request.getEpicId() != null) {
            Epic epic = epicRepository.findById(request.getEpicId())
                    .orElseThrow(() -> new BusinessException("Epic not found"));
            entity.setEpic(epic);
        }

        if (request.getSprintId() != null) {
            Sprint sprint = sprintRepository.findById(request.getSprintId())
                    .orElseThrow(() -> new BusinessException("Sprint not found"));
            entity.setSprint(sprint);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public StoryResponse update(UUID id, StoryRequest request) {
        Story entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Story not found"));

        mapper.updateEntity(request, entity);

        if (request.getEpicId() != null) {
            Epic epic = epicRepository.findById(request.getEpicId())
                    .orElseThrow(() -> new BusinessException("Epic not found"));
            entity.setEpic(epic);
        }

        if (request.getSprintId() != null) {
            Sprint sprint = sprintRepository.findById(request.getSprintId())
                    .orElseThrow(() -> new BusinessException("Sprint not found"));
            entity.setSprint(sprint);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public StoryResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Story not found"));
    }

    @Override
    public PageResponse<StoryResponse> getAll(StoryFilter filter, Pageable pageable) {
        var page = repository.findAll(StorySpecifications.withFilter(filter), pageable)
                .map(mapper::toDto);
        return PageResponse.from(page);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("Story not found");

        repository.deleteById(id);
    }
}

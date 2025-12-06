package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Models.DTO.Requests.StoryRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Models.Entities.Epic;
import dz.corepulse.projectflow.Models.Entities.Sprint;
import dz.corepulse.projectflow.Models.Entities.Story;
import dz.corepulse.projectflow.Repositories.EpicRepository;
import dz.corepulse.projectflow.Repositories.SprintRepository;
import dz.corepulse.projectflow.Repositories.StoryRepository;
import dz.corepulse.projectflow.Mappers.StoryMapper;
import dz.corepulse.projectflow.Services.StoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

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
                    .orElseThrow(() -> new RuntimeException("Epic not found"));
            entity.setEpic(epic);
        }

        if (request.getSprintId() != null) {
            Sprint sprint = sprintRepository.findById(request.getSprintId())
                    .orElseThrow(() -> new RuntimeException("Sprint not found"));
            entity.setSprint(sprint);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public StoryResponse update(UUID id, StoryRequest request) {
        Story entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        mapper.updateEntity(request, entity);

        if (request.getEpicId() != null) {
            Epic epic = epicRepository.findById(request.getEpicId())
                    .orElseThrow(() -> new RuntimeException("Epic not found"));
            entity.setEpic(epic);
        }

        if (request.getSprintId() != null) {
            Sprint sprint = sprintRepository.findById(request.getSprintId())
                    .orElseThrow(() -> new RuntimeException("Sprint not found"));
            entity.setSprint(sprint);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public StoryResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Story not found"));
    }

    @Override
    public List<StoryResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Story not found");

        repository.deleteById(id);
    }
}

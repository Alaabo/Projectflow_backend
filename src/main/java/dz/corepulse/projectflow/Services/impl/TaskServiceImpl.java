package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Mappers.TaskMapper;
import dz.corepulse.projectflow.Models.DTO.Requests.TaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Models.Entities.Task;
import dz.corepulse.projectflow.Repositories.SprintRepository;
import dz.corepulse.projectflow.Repositories.StoryRepository;
import dz.corepulse.projectflow.Repositories.TaskRepository;
import dz.corepulse.projectflow.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final StoryRepository storyRepository;
    private final SprintRepository sprintRepository;
    private final TaskMapper mapper;

    @Override
    public TaskResponse create(TaskRequest request) {
        Task entity = mapper.toEntity(request);

        if (request.getStoryId() != null) {
            var story = storyRepository.findById(request.getStoryId())
                    .orElseThrow(() -> new RuntimeException("Story not found"));
            entity.setStory(story);
        }

        if (request.getSprintId() != null) {
            var sprint = sprintRepository.findById(request.getSprintId())
                    .orElseThrow(() -> new RuntimeException("Sprint not found"));
            entity.setSprint(sprint);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public TaskResponse update(UUID id, TaskRequest request) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        mapper.updateEntity(request, entity);

        if (request.getStoryId() != null) {
            var story = storyRepository.findById(request.getStoryId())
                    .orElseThrow(() -> new RuntimeException("Story not found"));
            entity.setStory(story);
        }

        if (request.getSprintId() != null) {
            var sprint = sprintRepository.findById(request.getSprintId())
                    .orElseThrow(() -> new RuntimeException("Sprint not found"));
            entity.setSprint(sprint);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public TaskResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<TaskResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Task not found");
        repository.deleteById(id);
    }
}


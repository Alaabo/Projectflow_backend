package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.TaskMapper;
import dz.corepulse.projectflow.Models.DTO.Filters.TaskFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.TaskRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.Entities.Task;
import dz.corepulse.projectflow.Repositories.SprintRepository;
import dz.corepulse.projectflow.Repositories.StoryRepository;
import dz.corepulse.projectflow.Repositories.TaskRepository;
import dz.corepulse.projectflow.Repositories.UserRepository;
import dz.corepulse.projectflow.Repositories.Specifications.TaskSpecifications;
import dz.corepulse.projectflow.Services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final StoryRepository storyRepository;
    private final SprintRepository sprintRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    @Override
    public TaskResponse create(TaskRequest request) {
        Task entity = mapper.toEntity(request);

        if (request.getStoryId() != null) {
            var story = storyRepository.findById(request.getStoryId())
                    .orElseThrow(() -> new BusinessException("Story not found"));
            entity.setStory(story);
        }

        if (request.getSprintId() != null) {
            var sprint = sprintRepository.findById(request.getSprintId())
                    .orElseThrow(() -> new BusinessException("Sprint not found"));
            entity.setSprint(sprint);
        }

        if (request.getAssignedUserId() != null) {
            var user = userRepository.findById(request.getAssignedUserId())
                    .orElseThrow(() -> new BusinessException("User not found"));
            entity.setAssignedUser(user);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public TaskResponse update(UUID id, TaskRequest request) {
        Task entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Task not found"));

        mapper.updateEntity(request, entity);

        if (request.getStoryId() != null) {
            var story = storyRepository.findById(request.getStoryId())
                    .orElseThrow(() -> new BusinessException("Story not found"));
            entity.setStory(story);
        }

        if (request.getSprintId() != null) {
            var sprint = sprintRepository.findById(request.getSprintId())
                    .orElseThrow(() -> new BusinessException("Sprint not found"));
            entity.setSprint(sprint);
        }

        if (request.getAssignedUserId() != null) {
            var user = userRepository.findById(request.getAssignedUserId())
                    .orElseThrow(() -> new BusinessException("User not found"));
            entity.setAssignedUser(user);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public TaskResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Task not found"));
    }

    @Override
    public PageResponse<TaskResponse> getAll(TaskFilter filter, Pageable pageable) {
        var page = repository.findAll(TaskSpecifications.withFilter(filter), pageable)
                .map(mapper::toDto);
        return PageResponse.from(page);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("Task not found");
        repository.deleteById(id);
    }
}

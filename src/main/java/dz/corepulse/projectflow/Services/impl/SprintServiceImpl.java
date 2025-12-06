package dz.corepulse.projectflow.Services.impl;


import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.SprintMapper;
import dz.corepulse.projectflow.Mappers.StoryMapper;
import dz.corepulse.projectflow.Mappers.TaskMapper;
import dz.corepulse.projectflow.Models.DTO.Filters.SprintFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.SprintRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.SprintResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.Entities.Project;
import dz.corepulse.projectflow.Models.Entities.Sprint;
import dz.corepulse.projectflow.Models.Entities.Story;
import dz.corepulse.projectflow.Models.Entities.Task;
import dz.corepulse.projectflow.Repositories.ProjectRepository;
import dz.corepulse.projectflow.Repositories.SprintRepository;
import dz.corepulse.projectflow.Repositories.StoryRepository;
import dz.corepulse.projectflow.Repositories.TaskRepository;
import dz.corepulse.projectflow.Repositories.Specifications.SprintSpecifications;
import dz.corepulse.projectflow.Services.SprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements SprintService {

    private final SprintRepository repository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final StoryRepository storyRepository;
    private final SprintMapper mapper;
    private final TaskMapper taskMapper;
    private final StoryMapper storyMapper;

    @Override
    public SprintResponse create(SprintRequest request) {
        Sprint entity = mapper.toEntity(request);

        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new BusinessException("Project not found"));
            entity.setProject(project);
        }

        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public SprintResponse update(UUID id, SprintRequest request) {
        Sprint entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Sprint not found"));

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
    public SprintResponse get(UUID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new BusinessException("Sprint not found"));
    }

    @Override
    public PageResponse<SprintResponse> getAll(SprintFilter filter, Pageable pageable) {
        var page = repository.findAll(SprintSpecifications.withFilter(filter), pageable)
                .map(mapper::toDto);
        return PageResponse.from(page);
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id))
            throw new BusinessException("Sprint not found");

        repository.deleteById(id);
    }

    @Override
    public TaskResponse addTaskToSprint(UUID sprintId, UUID taskId) {
        Sprint sprint = repository.findById(sprintId)
                .orElseThrow(() -> new BusinessException("Sprint not found"));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new BusinessException("Task not found"));

        Project sprintProject = sprint.getProject();
        if (sprintProject == null) {
            throw new BusinessException("Sprint is not linked to a project");
        }

        Project taskProject = task.getStory() != null && task.getStory().getEpic() != null
                ? task.getStory().getEpic().getProject()
                : null;

        if (taskProject == null) {
            throw new BusinessException("Task is not linked to a project through its story");
        }

        if (!Objects.equals(sprintProject.getId(), taskProject.getId())) {
            throw new BusinessException("Task and sprint must belong to the same project");
        }

        task.setSprint(sprint);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public StoryResponse moveStory(UUID fromSprintId, UUID storyId, UUID toSprintId) {
        Sprint fromSprint = repository.findById(fromSprintId)
                .orElseThrow(() -> new BusinessException("Source sprint not found"));
        Sprint toSprint = repository.findById(toSprintId)
                .orElseThrow(() -> new BusinessException("Destination sprint not found"));
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new BusinessException("Story not found"));

        if (story.getSprint() == null || !Objects.equals(story.getSprint().getId(), fromSprint.getId())) {
            throw new BusinessException("Story does not belong to the source sprint");
        }

        Project storyProject = story.getEpic() != null ? story.getEpic().getProject() : null;
        Project destinationProject = toSprint.getProject();

        if (storyProject == null || destinationProject == null ||
                !Objects.equals(storyProject.getId(), destinationProject.getId())) {
            throw new BusinessException("Cannot move story to sprint of another project");
        }

        story.setSprint(toSprint);
        storyRepository.save(story);
        return storyMapper.toDto(story);
    }
}

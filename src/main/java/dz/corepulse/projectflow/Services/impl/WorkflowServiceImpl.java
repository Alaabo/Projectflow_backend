package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Mappers.StoryMapper;
import dz.corepulse.projectflow.Mappers.SubTaskMapper;
import dz.corepulse.projectflow.Mappers.TaskMapper;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.SubTaskResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import dz.corepulse.projectflow.Models.Entities.Story;
import dz.corepulse.projectflow.Models.Entities.SubTask;
import dz.corepulse.projectflow.Models.Entities.Task;
import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import dz.corepulse.projectflow.Models.Enums.SubTaskStatus;
import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import dz.corepulse.projectflow.Repositories.StoryRepository;
import dz.corepulse.projectflow.Repositories.SubTaskRepository;
import dz.corepulse.projectflow.Repositories.TaskRepository;
import dz.corepulse.projectflow.Services.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService {

    private static final String INVALID_TRANSITION = "Invalid workflow transition";

    private final StoryRepository storyRepository;
    private final TaskRepository taskRepository;
    private final SubTaskRepository subTaskRepository;
    private final StoryMapper storyMapper;
    private final TaskMapper taskMapper;
    private final SubTaskMapper subTaskMapper;

    @Override
    @Transactional
    public Object changeStatus(String entityType, UUID entityId, String newStatus) {
        WorkflowEntityType type = WorkflowEntityType.from(entityType);
        return switch (type) {
            case STORY -> changeStoryStatus(entityId, parseStatus(newStatus, StoryStatus.class));
            case TASK -> changeTaskStatus(entityId, parseStatus(newStatus, TaskStatus.class));
            case SUBTASK -> changeSubTaskStatus(entityId, parseStatus(newStatus, SubTaskStatus.class));
        };
    }

    private StoryResponse changeStoryStatus(UUID id, StoryStatus newStatus) {
        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Story not found"));
        StoryStatus current = story.getStatut() == null ? StoryStatus.TODO : story.getStatut();
        validateSequential(current, newStatus);

        if (current == newStatus) {
            return storyMapper.toDto(story);
        }

        if (current == StoryStatus.TODO && newStatus == StoryStatus.IN_PROGRESS && story.getSprint() == null) {
            throw new BusinessException(INVALID_TRANSITION);
        }

        if (current == StoryStatus.IN_PROGRESS && newStatus == StoryStatus.REVIEW) {
            boolean hasTasks = story.getTasks() != null && !story.getTasks().isEmpty();
            boolean hasCompletedTask = story.getTasks().stream()
                    .anyMatch(task -> task.getStatut() == TaskStatus.DONE);
            if (hasTasks && !hasCompletedTask) {
                throw new BusinessException(INVALID_TRANSITION);
            }
        }

        if (current == StoryStatus.REVIEW && newStatus == StoryStatus.DONE) {
            boolean allTasksDone = story.getTasks().stream()
                    .allMatch(task -> task.getStatut() == TaskStatus.DONE);
            boolean allSubTasksDone = story.getTasks().stream()
                    .flatMap(task -> task.getSubtasks().stream())
                    .allMatch(subTask -> subTask.getStatut() == SubTaskStatus.DONE);
            if (!allTasksDone || !allSubTasksDone) {
                throw new BusinessException(INVALID_TRANSITION);
            }
        }

        story.setStatut(newStatus);
        storyRepository.save(story);
        return storyMapper.toDto(story);
    }

    private TaskResponse changeTaskStatus(UUID id, TaskStatus newStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Task not found"));
        TaskStatus current = task.getStatut() == null ? TaskStatus.TODO : task.getStatut();
        validateSequential(current, newStatus);

        if (current == newStatus) {
            return taskMapper.toDto(task);
        }

        if (current == TaskStatus.TODO && newStatus == TaskStatus.IN_PROGRESS && task.getSprint() == null) {
            throw new BusinessException(INVALID_TRANSITION);
        }

        if (current == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.REVIEW) {
            boolean hasSubTasks = task.getSubtasks() != null && !task.getSubtasks().isEmpty();
            boolean hasCompletedSubTasks = task.getSubtasks().stream()
                    .anyMatch(subTask -> subTask.getStatut() == SubTaskStatus.DONE);
            if (hasSubTasks && !hasCompletedSubTasks) {
                throw new BusinessException(INVALID_TRANSITION);
            }
        }

        if (current == TaskStatus.REVIEW && newStatus == TaskStatus.DONE) {
            boolean allSubTasksDone = task.getSubtasks().stream()
                    .allMatch(subTask -> subTask.getStatut() == SubTaskStatus.DONE);
            if (!allSubTasksDone) {
                throw new BusinessException(INVALID_TRANSITION);
            }
        }

        task.setStatut(newStatus);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    private SubTaskResponse changeSubTaskStatus(UUID id, SubTaskStatus newStatus) {
        SubTask subTask = subTaskRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Subtask not found"));
        SubTaskStatus current = subTask.getStatut() == null ? SubTaskStatus.TODO : subTask.getStatut();
        validateSequential(current, newStatus);

        if (current == newStatus) {
            return subTaskMapper.toDto(subTask);
        }

        if (current == SubTaskStatus.TODO && newStatus == SubTaskStatus.IN_PROGRESS) {
            Task parentTask = subTask.getTask();
            if (parentTask == null || parentTask.getSprint() == null) {
                throw new BusinessException(INVALID_TRANSITION);
            }
        }

        subTask.setStatut(newStatus);
        subTaskRepository.save(subTask);
        return subTaskMapper.toDto(subTask);
    }

    private <E extends Enum<E>> void validateSequential(Enum<E> current, Enum<E> target) {
        if (current == target) {
            return;
        }
        if (target.ordinal() - current.ordinal() != 1) {
            throw new BusinessException(INVALID_TRANSITION);
        }
    }

    private <E extends Enum<E>> E parseStatus(String value, Class<E> type) {
        try {
            return Enum.valueOf(type, value.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new BusinessException(INVALID_TRANSITION);
        }
    }

    private enum WorkflowEntityType {
        STORY("story", "stories"),
        TASK("task", "tasks"),
        SUBTASK("subtask", "subtasks");

        private final String[] aliases;

        WorkflowEntityType(String... aliases) {
            this.aliases = aliases;
        }

        static WorkflowEntityType from(String raw) {
            return Arrays.stream(values())
                    .filter(type -> Arrays.stream(type.aliases)
                            .anyMatch(alias -> Objects.equals(alias, raw.toLowerCase(Locale.ROOT))))
                    .findFirst()
                    .orElseThrow(() -> new BusinessException("Unsupported workflow entity type"));
        }
    }
}

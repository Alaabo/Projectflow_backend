package dz.corepulse.projectflow.Services.impl;

import dz.corepulse.projectflow.Exceptions.BusinessException;
import dz.corepulse.projectflow.Models.DTO.Responses.BurndownPoint;
import dz.corepulse.projectflow.Models.Entities.Sprint;
import dz.corepulse.projectflow.Models.Entities.Story;
import dz.corepulse.projectflow.Models.Entities.SubTask;
import dz.corepulse.projectflow.Models.Entities.Task;
import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import dz.corepulse.projectflow.Models.Enums.SubTaskStatus;
import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import dz.corepulse.projectflow.Repositories.ProjectRepository;
import dz.corepulse.projectflow.Repositories.SprintRepository;
import dz.corepulse.projectflow.Services.SprintAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SprintAnalyticsServiceImpl implements SprintAnalyticsService {

    private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BurndownPoint> getBurndown(UUID sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new BusinessException("Sprint not found"));
        if (sprint.getDateDebut() == null || sprint.getDateFin() == null) {
            throw new BusinessException("Sprint must have start and end dates");
        }

        LocalDate start = sprint.getDateDebut().toLocalDate();
        LocalDate end = sprint.getDateFin().toLocalDate();

        int totalPoints = sprint.getStories().stream()
                .mapToInt(story -> Optional.ofNullable(story.getPts()).orElse(0))
                .sum();

        Map<LocalDate, Double> completions = calculateCompletions(sprint, start, end);

        List<BurndownPoint> points = new ArrayList<>();
        double completed = 0;
        for (LocalDate day = start; !day.isAfter(end); day = day.plusDays(1)) {
            completed += completions.getOrDefault(day, 0D);
            int remaining = (int) Math.max(0, Math.round(totalPoints - completed));
            points.add(new BurndownPoint(day, remaining));
        }

        return points;
    }

    @Override
    @Transactional(readOnly = true)
    public int computeVelocity(UUID projectId, int lastNSprints) {
        if (lastNSprints <= 0) {
            throw new BusinessException("last parameter must be positive");
        }

        if (!projectRepository.existsById(projectId)) {
            throw new BusinessException("Project not found");
        }

        return sprintRepository.findByProjectIdOrderByDateDebutDesc(projectId).stream()
                .sorted(Comparator.comparing((Sprint sprint) ->
                        sprint.getDateDebut() != null ? sprint.getDateDebut() : LocalDateTime.MIN).reversed())
                .limit(lastNSprints)
                .mapToInt(this::completedStoryPoints)
                .sum();
    }

    @Override
    @Transactional(readOnly = true)
    public int computeSprintProgress(UUID sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new BusinessException("Sprint not found"));
        int total = sprint.getStories().stream()
                .mapToInt(story -> Optional.ofNullable(story.getPts()).orElse(0))
                .sum();
        if (total == 0) {
            return 0;
        }

        int completed = completedStoryPoints(sprint);
        return (int) Math.round((completed * 100.0) / total);
    }

    private int completedStoryPoints(Sprint sprint) {
        return sprint.getStories().stream()
                .filter(story -> story.getStatut() == StoryStatus.DONE)
                .mapToInt(story -> Optional.ofNullable(story.getPts()).orElse(0))
                .sum();
    }

    private Map<LocalDate, Double> calculateCompletions(Sprint sprint, LocalDate start, LocalDate end) {
        Map<LocalDate, Double> completions = new TreeMap<>();

        for (Story story : sprint.getStories()) {
            int storyPoints = Optional.ofNullable(story.getPts()).orElse(0);
            if (storyPoints == 0) {
                continue;
            }

            int unitCount = story.getTasks().stream()
                    .mapToInt(task -> task.getSubtasks().isEmpty() ? 1 : task.getSubtasks().size())
                    .sum();

            if (unitCount == 0) {
                if (story.getStatut() == StoryStatus.DONE) {
                    addCompletion(completions, resolveDate(story.getUpdatedAt(), story.getDateFin(), sprint.getDateFin()), storyPoints, start, end);
                }
                continue;
            }

            double pointsPerUnit = storyPoints / (double) unitCount;
            double credited = 0;

            for (Task task : story.getTasks()) {
                if (task.getSubtasks().isEmpty()) {
                    if (task.getStatut() == TaskStatus.DONE) {
                        addCompletion(completions,
                                resolveDate(task.getUpdatedAt(), task.getDateDebut(), sprint.getDateFin()),
                                pointsPerUnit, start, end);
                        credited += pointsPerUnit;
                    }
                } else {
                    for (SubTask subTask : task.getSubtasks()) {
                        if (subTask.getStatut() == SubTaskStatus.DONE) {
                            addCompletion(completions,
                                    resolveDate(subTask.getUpdatedAt(), task.getUpdatedAt(), sprint.getDateFin()),
                                    pointsPerUnit, start, end);
                            credited += pointsPerUnit;
                        }
                    }
                }
            }

            double remainder = storyPoints - credited;
            if (remainder > 0 && story.getStatut() == StoryStatus.DONE) {
                addCompletion(completions,
                        resolveDate(story.getUpdatedAt(), story.getDateFin(), sprint.getDateFin()),
                        remainder, start, end);
            }
        }

        return completions;
    }

    private LocalDate resolveDate(LocalDateTime... candidates) {
        for (LocalDateTime candidate : candidates) {
            if (candidate != null) {
                return candidate.toLocalDate();
            }
        }
        return LocalDate.now();
    }

    private void addCompletion(Map<LocalDate, Double> completions, LocalDate date, double points,
                               LocalDate start, LocalDate end) {
        LocalDate clamped = clamp(date, start, end);
        completions.merge(clamped, points, Double::sum);
    }

    private LocalDate clamp(LocalDate date, LocalDate start, LocalDate end) {
        if (date.isBefore(start)) {
            return start;
        }
        if (date.isAfter(end)) {
            return end;
        }
        return date;
    }
}

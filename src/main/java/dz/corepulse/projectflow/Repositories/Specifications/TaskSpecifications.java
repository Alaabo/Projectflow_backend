package dz.corepulse.projectflow.Repositories.Specifications;

import dz.corepulse.projectflow.Models.DTO.Filters.TaskFilter;
import dz.corepulse.projectflow.Models.Entities.Task;
import dz.corepulse.projectflow.Models.Enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;
import java.util.UUID;

public final class TaskSpecifications {

    private TaskSpecifications() {
    }

    public static Specification<Task> withFilter(TaskFilter filter) {
        if (filter == null) {
            return Specification.where(null);
        }

        return Specification.where(hasStatus(filter.getStatut()))
                .and(hasPriority(filter.getPriority()))
                .and(hasStory(filter.getStoryId()))
                .and(hasSprint(filter.getSprintId()));
    }

    private static Specification<Task> hasStatus(TaskStatus status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("statut"), status);
    }

    private static Specification<Task> hasPriority(String priority) {
        return (root, query, cb) -> {
            if (priority == null) {
                return null;
            }
            return cb.equal(cb.lower(root.get("priority")), priority.toLowerCase(Locale.ROOT));
        };
    }

    private static Specification<Task> hasStory(UUID storyId) {
        return (root, query, cb) -> {
            if (storyId == null) {
                return null;
            }
            return cb.equal(root.get("story").get("id"), storyId);
        };
    }

    private static Specification<Task> hasSprint(UUID sprintId) {
        return (root, query, cb) -> {
            if (sprintId == null) {
                return null;
            }
            return cb.equal(root.get("sprint").get("id"), sprintId);
        };
    }
}

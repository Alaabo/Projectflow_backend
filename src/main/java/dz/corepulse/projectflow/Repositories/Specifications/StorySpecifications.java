package dz.corepulse.projectflow.Repositories.Specifications;

import dz.corepulse.projectflow.Models.DTO.Filters.StoryFilter;
import dz.corepulse.projectflow.Models.Entities.Story;
import dz.corepulse.projectflow.Models.Enums.StoryStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;
import java.util.UUID;

public final class StorySpecifications {

    private StorySpecifications() {
    }

    public static Specification<Story> withFilter(StoryFilter filter) {
        if (filter == null) {
            return Specification.where(null);
        }

        return Specification.where(hasStatus(filter.getStatut()))
                .and(hasPriority(filter.getPriority()))
                .and(hasSprint(filter.getSprintId()));
    }

    private static Specification<Story> hasStatus(StoryStatus status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("statut"), status);
    }

    private static Specification<Story> hasPriority(String priority) {
        return (root, query, cb) -> {
            if (priority == null) {
                return null;
            }
            return cb.equal(cb.lower(root.get("priority")), priority.toLowerCase(Locale.ROOT));
        };
    }

    private static Specification<Story> hasSprint(UUID sprintId) {
        return (root, query, cb) -> {
            if (sprintId == null) {
                return null;
            }
            return cb.equal(root.get("sprint").get("id"), sprintId);
        };
    }
}

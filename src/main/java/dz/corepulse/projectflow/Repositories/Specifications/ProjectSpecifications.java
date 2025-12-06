package dz.corepulse.projectflow.Repositories.Specifications;

import dz.corepulse.projectflow.Models.DTO.Filters.ProjectFilter;
import dz.corepulse.projectflow.Models.Entities.Project;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

public final class ProjectSpecifications {

    private ProjectSpecifications() {
    }

    public static Specification<Project> withFilter(ProjectFilter filter) {
        if (filter == null) {
            return Specification.where(null);
        }

        return Specification.where(hasStatut(filter.getStatut()))
                .and(hasDateDebutFrom(filter.getDateDebutFrom()))
                .and(hasDateDebutTo(filter.getDateDebutTo()));
    }

    private static Specification<Project> hasStatut(String statut) {
        return (root, query, cb) -> {
            if (statut == null) {
                return null;
            }
            return cb.equal(cb.lower(root.get("statut")), statut.toLowerCase(Locale.ROOT));
        };
    }

    private static Specification<Project> hasDateDebutFrom(LocalDateTime from) {
        return (root, query, cb) -> Optional.ofNullable(from)
                .map(value -> cb.greaterThanOrEqualTo(root.get("dateDebut"), value))
                .orElse(null);
    }

    private static Specification<Project> hasDateDebutTo(LocalDateTime to) {
        return (root, query, cb) -> Optional.ofNullable(to)
                .map(value -> cb.lessThanOrEqualTo(root.get("dateDebut"), value))
                .orElse(null);
    }
}

package dz.corepulse.projectflow.Repositories.Specifications;

import dz.corepulse.projectflow.Models.DTO.Filters.SprintFilter;
import dz.corepulse.projectflow.Models.Entities.Sprint;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

public final class SprintSpecifications {

    private SprintSpecifications() {
    }

    public static Specification<Sprint> withFilter(SprintFilter filter) {
        if (filter == null) {
            return Specification.where(null);
        }

        return Specification.where(hasStatut(filter.getStatut()))
                .and(hasDateDebutFrom(filter.getDateDebutFrom()))
                .and(hasDateDebutTo(filter.getDateDebutTo()))
                .and(hasDateFinFrom(filter.getDateFinFrom()))
                .and(hasDateFinTo(filter.getDateFinTo()));
    }

    private static Specification<Sprint> hasStatut(String statut) {
        return (root, query, cb) -> {
            if (statut == null) {
                return null;
            }
            return cb.equal(cb.lower(root.get("statut")), statut.toLowerCase(Locale.ROOT));
        };
    }

    private static Specification<Sprint> hasDateDebutFrom(LocalDateTime from) {
        return (root, query, cb) -> Optional.ofNullable(from)
                .map(value -> cb.greaterThanOrEqualTo(root.get("dateDebut"), value))
                .orElse(null);
    }

    private static Specification<Sprint> hasDateDebutTo(LocalDateTime to) {
        return (root, query, cb) -> Optional.ofNullable(to)
                .map(value -> cb.lessThanOrEqualTo(root.get("dateDebut"), value))
                .orElse(null);
    }

    private static Specification<Sprint> hasDateFinFrom(LocalDateTime from) {
        return (root, query, cb) -> Optional.ofNullable(from)
                .map(value -> cb.greaterThanOrEqualTo(root.get("dateFin"), value))
                .orElse(null);
    }

    private static Specification<Sprint> hasDateFinTo(LocalDateTime to) {
        return (root, query, cb) -> Optional.ofNullable(to)
                .map(value -> cb.lessThanOrEqualTo(root.get("dateFin"), value))
                .orElse(null);
    }
}

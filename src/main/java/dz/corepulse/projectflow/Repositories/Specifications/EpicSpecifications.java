package dz.corepulse.projectflow.Repositories.Specifications;

import dz.corepulse.projectflow.Models.DTO.Filters.EpicFilter;
import dz.corepulse.projectflow.Models.Entities.Epic;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public final class EpicSpecifications {

    private EpicSpecifications() {
    }

    public static Specification<Epic> withFilter(EpicFilter filter) {
        if (filter == null) {
            return Specification.where(null);
        }

        return Specification.where(hasStatut(filter.getStatut()))
                .and(hasPriority(filter.getPriority()));
    }

    private static Specification<Epic> hasStatut(String statut) {
        return (root, query, cb) -> {
            if (statut == null) {
                return null;
            }
            return cb.equal(cb.lower(root.get("statut")), statut.toLowerCase(Locale.ROOT));
        };
    }

    private static Specification<Epic> hasPriority(String priority) {
        return (root, query, cb) -> {
            if (priority == null) {
                return null;
            }
            return cb.equal(cb.lower(root.get("priority")), priority.toLowerCase(Locale.ROOT));
        };
    }
}

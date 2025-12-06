package dz.corepulse.projectflow.Services;

import dz.corepulse.projectflow.Models.DTO.Responses.BurndownPoint;

import java.util.List;
import java.util.UUID;

public interface SprintAnalyticsService {

    List<BurndownPoint> getBurndown(UUID sprintId);

    int computeVelocity(UUID projectId, int lastNSprints);

    int computeSprintProgress(UUID sprintId);
}

package dz.corepulse.projectflow.Services;

import java.util.UUID;

public interface WorkflowService {

    Object changeStatus(String entityType, UUID entityId, String newStatus);
}

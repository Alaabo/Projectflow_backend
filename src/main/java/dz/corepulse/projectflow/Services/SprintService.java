package dz.corepulse.projectflow.Services;

import dz.corepulse.projectflow.Models.DTO.Filters.SprintFilter;
import dz.corepulse.projectflow.Models.DTO.Requests.SprintRequest;
import dz.corepulse.projectflow.Models.DTO.Responses.PageResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.SprintResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.StoryResponse;
import dz.corepulse.projectflow.Models.DTO.Responses.TaskResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SprintService {

    SprintResponse create(SprintRequest request);

    SprintResponse update(UUID id, SprintRequest request);

    SprintResponse get(UUID id);

    PageResponse<SprintResponse> getAll(SprintFilter filter, Pageable pageable);

    void delete(UUID id);

    TaskResponse addTaskToSprint(UUID sprintId, UUID taskId);

    StoryResponse moveStory(UUID fromSprintId, UUID storyId, UUID toSprintId);
}

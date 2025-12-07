package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Services.WorkflowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Workflow", description = "Update workflow status across entities")
public class WorkflowController {

    private final WorkflowService workflowService;

    @PatchMapping("/{type}/{id}/status")
    @Operation(summary = "Change workflow status", description = "Changes status for a sprint, story, task, or subtask.")
    public ApiResponse<Object> changeStatus(@Parameter(description = "Entity type (sprint, story, task, subtask)") @PathVariable String type,
                                            @Parameter(description = "Identifier of the entity") @PathVariable UUID id,
                                            @Parameter(description = "New status value") @RequestParam("value") String value) {
        return ApiResponse.success(workflowService.changeStatus(type, id, value));
    }
}

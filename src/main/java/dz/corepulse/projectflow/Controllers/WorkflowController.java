package dz.corepulse.projectflow.Controllers;

import dz.corepulse.projectflow.Models.DTO.Responses.ApiResponse;
import dz.corepulse.projectflow.Services.WorkflowService;
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
public class WorkflowController {

    private final WorkflowService workflowService;

    @PatchMapping("/{type}/{id}/status")
    public ApiResponse<Object> changeStatus(@PathVariable String type,
                                            @PathVariable UUID id,
                                            @RequestParam("value") String value) {
        return ApiResponse.success(workflowService.changeStatus(type, id, value));
    }
}

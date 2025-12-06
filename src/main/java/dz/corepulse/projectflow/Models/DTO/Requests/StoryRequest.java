package dz.corepulse.projectflow.Models.DTO.Requests;



import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StoryRequest {

    private String storyName;
    private String desc;
    private String statut;
    private String priority;
    private Integer pts;
    private Integer timeLeft;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private UUID epicId;
    private UUID sprintId;
}

package dz.corepulse.projectflow.Models.DTO.Requests;


import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserRequest {
    private String name;

    // IDs of assigned profiles
    private List<UUID> profileIds;

    // IDs of groups this user belongs to
    private List<UUID> groupIds;
}

package working.hotellakewood.DTO;

import lombok.Getter;
import lombok.Setter;
import working.hotellakewood.entity.Role;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;


    private List<ReservationDTO> reservationsDTO = new ArrayList<>();

}

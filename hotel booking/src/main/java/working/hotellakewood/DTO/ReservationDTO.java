package working.hotellakewood.DTO;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationDTO {
    private Long reservationId;
    private String userName;
    private String gmailId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long persons;
    private Long totalCost;
    private String confirmetionCode;

    public void setConfirmetionCode() {
        confirmetionCode = String.valueOf((long) (Math.random() * 9_000_000_000L) + 1_000_000_000L);
    }


    private RoomDTO roomDTO;

    private UserDTO userDTO;
}

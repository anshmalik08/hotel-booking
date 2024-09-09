package working.hotellakewood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations_db")
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

}
package working.hotellakewood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import working.hotellakewood.entity.Reservations;

public interface ReservationRepository extends JpaRepository<Reservations, Long> {
    Reservations findByConfirmetionCode(String code);
//    List<Reservations> findByUser(Long id);
}

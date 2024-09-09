package working.hotellakewood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import working.hotellakewood.entity.Location;

public interface LocationsRepository extends JpaRepository<Location, Long> {
    boolean existsByLocationName(String lName);
}

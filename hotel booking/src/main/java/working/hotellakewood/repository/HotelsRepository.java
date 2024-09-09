package working.hotellakewood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import working.hotellakewood.entity.Hotel;

public interface HotelsRepository extends JpaRepository<Hotel, Long> {
}

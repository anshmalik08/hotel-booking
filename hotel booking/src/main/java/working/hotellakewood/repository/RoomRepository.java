package working.hotellakewood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import working.hotellakewood.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}

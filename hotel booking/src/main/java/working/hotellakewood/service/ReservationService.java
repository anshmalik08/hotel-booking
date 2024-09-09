package working.hotellakewood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import working.hotellakewood.DTO.ReservationDTO;
import working.hotellakewood.DTO.UserDTO;
import working.hotellakewood.entity.Reservations;
import working.hotellakewood.entity.Room;
import working.hotellakewood.entity.User;
import working.hotellakewood.mapper.ReservationMapper;
import working.hotellakewood.mapper.RoomMapper;
import working.hotellakewood.mapper.UserMapper;
import working.hotellakewood.repository.ReservationRepository;
import working.hotellakewood.repository.RoomRepository;
import working.hotellakewood.repository.UserRepository;

@Service
public class ReservationService {
        @Autowired
    private ReservationRepository rRepo;
    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private UserRepository userRepo;



    public String reserve(Long roomId, Long userId, Reservations reservation) {
        Room room = roomRepo.findById(roomId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);

        if (user != null && room != null) {
            reservation.setUser(user);
            reservation.setRoom(room);
            reservation.setConfirmetionCode();
            user.getReservations().add(reservation);
            userRepo.save(user);
            System.out.println("userDto.username "+user.getFirstName());
            room.getReservations().add(reservation);
            roomRepo.save(room);

            return "Room booked";
        } else {
            return "User or Room not found";
        }
    }
}

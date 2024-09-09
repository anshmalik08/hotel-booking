package working.hotellakewood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import working.hotellakewood.DTO.HotelDTO;
import working.hotellakewood.DTO.ReservationDTO;
import working.hotellakewood.DTO.RoomDTO;
import working.hotellakewood.DTO.UserDTO;
import working.hotellakewood.entity.Reservations;
import working.hotellakewood.entity.User;
import working.hotellakewood.mapper.HotelMapper;
import working.hotellakewood.mapper.RoomMapper;
import working.hotellakewood.mapper.UserMapper;
import working.hotellakewood.repository.ReservationRepository;
import working.hotellakewood.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository uRepo;
    @Autowired
    private ReservationRepository rRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO getUserById(Long id){
//        User user = uRepo.findById(id).get();
        User u = uRepo.findById(id).get();
//        UserDTO u = UserMapper.toDto(u);

        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(u.getUserId());
        userDTO.setFirstName(u.getFirstName());
        userDTO.setLastName(u.getLastName());
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
//
//        u.getReservations().forEach(reservations -> {
//            reservations.getRoom().setReservations(null);
////          reservations.getRoom().setRoomTypes(null);
//            reservations.getRoom().setImageData(null);
//            reservations.setUser(null);
//            reservations.getRoom().getHotel().setRooms(null);
//            reservations.getRoom().getHotel().setImageData(null);
//            reservations.getRoom().getHotel().getLocation().setHotels(null);
//
//        });

        for (Reservations reservation:u.getReservations()
             ) {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setUserName(reservation.getUserName());
            reservationDTO.setPersons(reservation.getPersons());
            reservationDTO.setDateFrom(reservation.getDateFrom());
            reservationDTO.setTotalCost(reservation.getTotalCost());
            reservationDTO.setDateTo(reservation.getDateTo());
            reservationDTO.setConfirmetionCode(reservation.getConfirmetionCode());
            reservationDTO.setGmailId(reservation.getGmailId());
            reservationDTO.setReservationId(reservation.getReservationId());
            reservationDTO.setUserDTO(null);

            RoomDTO roomDTO = RoomMapper.getRoomDtoForUserGetById(reservation);
            roomDTO.setHotelDTO(HotelMapper.toDtoForRoom(reservation.getRoom().getHotel()));
            reservationDTO.setRoomDTO(roomDTO);
            reservationDTOS.add(reservationDTO);
        }
        userDTO.setReservationsDTO(reservationDTOS);
        return userDTO;
    }



    public UserDTO updateUser(Long id,User updatedUser){
        User user = uRepo.findById(id).get();
        if (updatedUser.getFirstName() != "") {
            System.out.println("this is when name is not empty");
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setPassword(user.getPassword());
            user.setRole(user.getRole());
            user.setReservations(user.getReservations());
            uRepo.save(user);
        } else {
            System.out.println("this is when name is empty");
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setRole(user.getRole());
            user.setReservations(user.getReservations());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Uncomment this line
            uRepo.save(user);
        }
        UserDTO userDTO = UserMapper.toDto(user);
        userDTO.setReservationsDTO(null);

//        user.setRole(null);
//        user.setPassword(null);
        return userDTO;
    }

    public String updateUserPassword(String id, User updatedUser) {
        User user = uRepo.findByEmail(id).get();

        if (user.getUserId()!=null){
            user.setFirstName(user.getFirstName());
            user.setLastName(user.getLastName());
            user.setRole(user.getRole());
            user.setReservations(user.getReservations());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            uRepo.save(user);
        }

        else {
            return "not Present";
        }

        return "updated successfully";
    }
}

package working.hotellakewood.mapper;

import working.hotellakewood.DTO.ReservationDTO;
import working.hotellakewood.DTO.UserDTO;
import working.hotellakewood.entity.Reservations;
import working.hotellakewood.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setUserId(user.getUserId());

        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservations reservation: user.getReservations()
             ) {
            reservationDTOS.add(ReservationMapper.toDtoForUser(reservation));
        }
        userDTO.setReservationsDTO(reservationDTOS);

        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        return user;
    }
}

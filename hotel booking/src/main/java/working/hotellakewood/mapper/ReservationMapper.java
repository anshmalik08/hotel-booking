package working.hotellakewood.mapper;

import working.hotellakewood.DTO.ReservationDTO;
import working.hotellakewood.entity.Reservations;

public class ReservationMapper {
public static ReservationDTO toDto(Reservations reservations){
     ReservationDTO reservationDTO = new ReservationDTO();

     reservationDTO.setReservationId(reservations.getReservationId());
     reservationDTO.setConfirmetionCode();
     reservationDTO.setPersons(reservations.getPersons());
     reservationDTO.setGmailId(reservations.getGmailId());
     reservationDTO.setDateFrom(reservations.getDateFrom());
     reservationDTO.setDateTo(reservations.getDateTo());
     reservationDTO.setTotalCost(reservations.getTotalCost());
     reservationDTO.setUserName(reservations.getUserName());
     reservationDTO.setUserDTO(UserMapper.toDto(reservations.getUser()));
     reservationDTO.setRoomDTO(RoomMapper.toDto(reservations.getRoom()));

     return reservationDTO;
}
public static ReservationDTO toDtoForUser(Reservations reservations){
     ReservationDTO reservationDTO = new ReservationDTO();

     reservationDTO.setReservationId(reservations.getReservationId());
     reservationDTO.setConfirmetionCode();
     reservationDTO.setPersons(reservations.getPersons());
     reservationDTO.setGmailId(reservations.getGmailId());
     reservationDTO.setDateFrom(reservations.getDateFrom());
     reservationDTO.setDateTo(reservations.getDateTo());
     reservationDTO.setTotalCost(reservations.getTotalCost());
     reservationDTO.setUserName(reservations.getUserName());
//     reservationDTO.setUserDTO(UserMapper.toDto(reservations.getUser()));
//     reservationDTO.setRoomDTO(RoomMapper.toDto(reservations.getRoom()));

     return reservationDTO;
}

     public static Reservations toEntity(ReservationDTO reservationDTO){
          Reservations reservation = new Reservations();

          reservation.setConfirmetionCode();
          reservation.setPersons(reservationDTO.getPersons());
          reservation.setGmailId(reservationDTO.getGmailId());
          reservation.setDateFrom(reservationDTO.getDateFrom());
          reservation.setDateTo(reservationDTO.getDateTo());
          reservation.setTotalCost(reservationDTO.getTotalCost());
          reservation.setUserName(reservationDTO.getUserName());
//          reservation.setUser(UserMapper.toEntity(reservationDTO.getUserDTO()));
//          reservation.setRoom(RoomMapper.toEntity(reservationDTO.getRoomDTO()));

          return reservation;
     }
}

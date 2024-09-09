package working.hotellakewood.mapper;

import working.hotellakewood.DTO.HotelDTO;
import working.hotellakewood.DTO.ReservationDTO;
import working.hotellakewood.DTO.RoomDTO;
import working.hotellakewood.entity.Reservations;
import working.hotellakewood.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomMapper {
    public static RoomDTO toDto(Room room){

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomNo(room.getRoomNo());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setRoomType(room.getRoomType());
//        roomDTO.setHotel(HotelMapper.toDTO(room.getHotel()));
        String imageUrl = "http://localhost:9191/api/v1/rooms/room/" + room.getRoomId() + "/image";
        roomDTO.setImageUrl(imageUrl);
        List<ReservationDTO> resDto = new ArrayList<>();
        for (Reservations reservation: room.getReservations()
             ) {
            resDto.add(ReservationMapper.toDtoForUser(reservation));
        }
        roomDTO.setReservationDTOS(resDto);

        return roomDTO;
    }
    public static RoomDTO getRoomDtoForUserGetById(Reservations reservation) {
        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setDescription(reservation.getRoom().getDescription());
        roomDTO.setFileName(reservation.getRoom().getFileName());
        roomDTO.setImageData(null);
        roomDTO.setImageUrl(null);
        roomDTO.setReservationDTOS(null);
        roomDTO.setPrice(reservation.getRoom().getPrice());
        roomDTO.setRoomType(reservation.getRoom().getRoomType());
        roomDTO.setRoomId(reservation.getRoom().getRoomId());
        roomDTO.setRoomNo(reservation.getRoom().getRoomNo());

        return roomDTO;
    }

    public static RoomDTO toDtoForHotel(Room room){

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomNo(room.getRoomNo());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setHotelDTO(HotelMapper.toDTO(room.getHotel()));
        roomDTO.setRoomType(room.getRoomType());

        return roomDTO;
    }
    public static Room toEntity(RoomDTO roomDTO){
        Room room = new Room();

        room.setRoomNo(roomDTO.getRoomNo());
        room.setDescription(roomDTO.getDescription());
        room.setPrice(roomDTO.getPrice());
        room.setRoomType(roomDTO.getRoomType());
        room.setHotel(HotelMapper.toEntityForRoom(roomDTO.getHotelDTO()));
        room.setImageData(roomDTO.getImageData());
        room.setFileName(roomDTO.getFileName());

        return room;

    }
}

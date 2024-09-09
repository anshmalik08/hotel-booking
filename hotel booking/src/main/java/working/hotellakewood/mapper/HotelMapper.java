package working.hotellakewood.mapper;

import working.hotellakewood.DTO.HotelDTO;
import working.hotellakewood.DTO.RoomDTO;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class HotelMapper {
    public static HotelDTO  toDTO(Hotel hotel){

        HotelDTO hotelDTO = new HotelDTO();

        hotelDTO.setHotelId(hotel.getHotelId());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setRating(hotel.getRating());
        hotelDTO.setImageUrl(hotel.getImageUrl());
        hotelDTO.setLocationDTO(LocationMapper.toDtoForHotelById(hotel.getLocation()));
        hotelDTO.setFileName(hotel.getFileName());
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: hotel.getRooms()
             ) {
            roomDTOS.add(RoomMapper.toDto(room));
        }
        hotelDTO.setRooms(roomDTOS);
        String imageUrl = "http://localhost:9191/api/v1/hotels/hotel/" + hotel.getHotelId() + "/image";
        hotelDTO.setImageUrl(imageUrl);

        return hotelDTO;
    }
    public static HotelDTO toDtoForRoom(Hotel hotel){

        HotelDTO hotelDTO = new HotelDTO();

        hotelDTO.setHotelId(hotel.getHotelId());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setRating(hotel.getRating());
        hotelDTO.setImageUrl(hotel.getImageUrl());
        hotelDTO.setLocationDTO(LocationMapper.toDto(hotel.getLocation()));
        hotelDTO.setFileName(hotel.getFileName());


        String imageUrl = "http://localhost:9191/api/v1/hotels/hotel/" + hotel.getHotelId() + "/image";
        hotelDTO.setImageUrl(imageUrl);

        return hotelDTO;
    }
    public static HotelDTO toDtoForLocation(Hotel hotel){

        HotelDTO hotelDTO = new HotelDTO();

        hotelDTO.setHotelId(hotel.getHotelId());
        hotelDTO.setHotelName(hotel.getHotelName());
        hotelDTO.setAddress(hotel.getAddress());
        hotelDTO.setRating(hotel.getRating());
        hotelDTO.setImageUrl(hotel.getImageUrl());
//        hotelDTO.setLocationDTO(LocationMapper.toDto(hotel.getLocation()));
        hotelDTO.setFileName(hotel.getFileName());


        String imageUrl = "http://localhost:9191/api/v1/hotels/hotel/" + hotel.getHotelId() + "/image";
        hotelDTO.setImageUrl(imageUrl);

        return hotelDTO;
    }

    public static Hotel toEntity(HotelDTO hotelDTO){

        Hotel hotel= new Hotel();

        hotel.setHotelId(hotelDTO.getHotelId());
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setRating(hotelDTO.getRating());
        hotel.setImageData(hotelDTO.getImageData());
        hotel.setLocation(LocationMapper.toEntity(hotelDTO.getLocationDTO()));
        hotel.setFileName(hotelDTO.getFileName());

        return hotel;
    }
    public static Hotel toEntityForRoom(HotelDTO hotelDTO){

        Hotel hotel= new Hotel();

        hotel.setHotelId(hotelDTO.getHotelId());
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setRating(hotelDTO.getRating());
        hotel.setImageData(hotelDTO.getImageData());
//        hotel.setLocation(LocationMapper.toEntity(hotelDTO.getLocationDTO()));
        hotel.setFileName(hotelDTO.getFileName());

        return hotel;
    }
}

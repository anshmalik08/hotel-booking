package working.hotellakewood.mapper;

import working.hotellakewood.DTO.HotelDTO;
import working.hotellakewood.DTO.LocationDTO;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.entity.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationMapper {
    public static LocationDTO toDto(Location location){
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setLocationId(location.getLocationId());
        locationDTO.setLocationName(location.getLocationName());
        List<HotelDTO> hotelDTOS = new ArrayList<>();
        for (Hotel hotel:location.getHotels()) {
            hotelDTOS.add(HotelMapper.toDTO(hotel));
        }
        locationDTO.setHotelsDTO(hotelDTOS);

        return locationDTO;
    }
    public static LocationDTO toDtoForHotelById(Location location){
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setLocationId(location.getLocationId());
        locationDTO.setLocationName(location.getLocationName());


        return locationDTO;
    }
    public static LocationDTO toDtoForAdminGetAll(Location location){
        LocationDTO locationDTO = new LocationDTO();

        locationDTO.setLocationId(location.getLocationId());
        locationDTO.setLocationName(location.getLocationName());
        List<HotelDTO> hotelDTOS = new ArrayList<>();
        for (Hotel hotel:location.getHotels()) {
            hotelDTOS.add(HotelMapper.toDtoForLocation(hotel));
        }
        locationDTO.setHotelsDTO(hotelDTOS);

        return locationDTO;
    }

    public static Location toEntity(LocationDTO locationDTO){
        Location location = new Location();

        location.setLocationId(locationDTO.getLocationId());
        location.setLocationName(locationDTO.getLocationName());
        List<Hotel> hotels = new ArrayList<>();
        for (HotelDTO hotelDTO:locationDTO.getHotelsDTO()) {
            hotels.add(HotelMapper.toEntity(hotelDTO));
        }
        location.setHotels(hotels);

        return location;
    }
}

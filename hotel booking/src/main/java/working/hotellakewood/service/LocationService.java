package working.hotellakewood.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import working.hotellakewood.DTO.LocationDTO;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.entity.Location;
import working.hotellakewood.mapper.LocationMapper;
import working.hotellakewood.repository.HotelsRepository;
import working.hotellakewood.repository.LocationsRepository;
import working.hotellakewood.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class LocationService {
    @Autowired
    private LocationsRepository lRepo;
    @Autowired
    private HotelsRepository hRepo;

    public String postLocation(LocationDTO locationDTO) {

        if (lRepo.existsByLocationName(locationDTO.getLocationName())) {
            return "exist";
        }
        Location location = LocationMapper.toEntity(locationDTO);
        lRepo.save(location);
        return "added successfully";
    }

    public String deleteById(Long id) {
        lRepo.deleteById(id);
        return "location deleted successfully";
    }

    public List<LocationDTO> getLocations() {
        List<Location> locations = lRepo.findAll();
        List<LocationDTO> resLocations = new ArrayList<>();
        locations.forEach(location -> {
            resLocations.add(LocationMapper.toDtoForAdminGetAll(location));
        });
        resLocations.forEach(locationDTO -> locationDTO.getHotelsDTO().forEach(hotelDTO -> hotelDTO.setLocationDTO(null)));
        return resLocations;
    }

    //    public List<Location> getLocation(){
//        List<Location> locations = lRepo.findAll();
////        List<Location> toSend =new ArrayList<>();
//
//        locations.forEach(location -> {
//            location.getHotels().forEach(hotel -> {
//                hotel.setImageData(null);
//                String imageUrl = "http://localhost:9191/api/v1/hotels/hotel/" + hotel.getHotelId() + "/image";
//                hotel.setImageUrl(imageUrl);
//                hotel.setLocation(null);
//                hotel.setAddress(null);
//                hotel.getRooms().forEach(room ->{
//                    String imageUrl1 = "http://localhost:9191/api/v1/rooms/room/" + room.getRoomId() + "/image";
//                    room.setImageData(null);
//                    room.setImageUrl(imageUrl1);
//                    room.getRoomTypes().forEach(roomType -> {
//                        roomType.setRooms(null);
//                    });
//                    room.setHotel(null);
//                    room.setReservations(null);
//                    System.out.println("heloo im aclled");
//                });
//            });
//
//        });
//        return locations;
//    }
    public ResponseEntity<byte[]> getRoomImage(@PathVariable Long id) {
        Hotel hotel = hRepo.findById(id).orElse(null);

        if (hotel == null || hotel.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }

        MediaType mediaType = switch (hotel.getFileName().substring(hotel.getFileName().lastIndexOf('.') + 1).toLowerCase()) {
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "pdf" -> MediaType.APPLICATION_PDF;

            // Add more cases for other image formats if needed
            default -> MediaType.APPLICATION_OCTET_STREAM; // Default to binary data if the type is unknown
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType); // adjust based on your image type

        return new ResponseEntity<>(ImageUtils.decompressImage(hotel.getImageData()), headers, HttpStatus.OK);
    }
}


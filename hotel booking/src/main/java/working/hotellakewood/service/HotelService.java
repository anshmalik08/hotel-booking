package working.hotellakewood.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import working.hotellakewood.DTO.HotelDTO;
import working.hotellakewood.DTO.LocationDTO;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.entity.Location;
import working.hotellakewood.mapper.HotelMapper;
import working.hotellakewood.mapper.LocationMapper;
import working.hotellakewood.repository.HotelsRepository;
import working.hotellakewood.repository.LocationsRepository;
import working.hotellakewood.repository.RoomRepository;
import working.hotellakewood.utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelsRepository hRepo;
    @Autowired
    private LocationsRepository lRepo;
    @Autowired
    private RoomRepository rRepo;

    //    @GetMapping("/hotel-by-id/{id}")
    public ResponseEntity<?> getHotelById(Long id) {
        Hotel hotel = hRepo.findById(id).get();
        HotelDTO hotelDTO = HotelMapper.toDTO(hotel);
                hotelDTO.getRooms().forEach(roomDTO -> {
                    roomDTO.setHotelDTO(null);
                    roomDTO.setReservationDTOS(null);
                });
                hotelDTO.setLocationDTO(null);
        return ResponseEntity.ok(hotelDTO);
    }

    public ResponseEntity<Hotel> update( Long id, Hotel updated) {
        Hotel h = hRepo.findById(id).get();
        Location l = lRepo.findById(updated.getLocation().getLocationId()).get();
        h.setLocation(l);
        l.getHotels().add(h);
        lRepo.save(l);
        return ResponseEntity.ok(h);
    }

    public ResponseEntity<String> addHotel( MultipartFile file,  String hotelDtoJson) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        HotelDTO hotelDTO = objectMapper.readValue(hotelDtoJson, HotelDTO.class);

        hotelDTO.setImageUrl(file.getOriginalFilename());
        hotelDTO.setImageData(ImageUtils.compressImage(file.getBytes()));
        Hotel hotel = HotelMapper.toEntity(hotelDTO);
        System.out.println("this is hotel");
        hotel.setFileName(file.getOriginalFilename());
        hRepo.save(hotel);

        // ... rest of the code ...

        return ResponseEntity.status(HttpStatus.OK).body("hotel added successfully");
    }

    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels = hRepo.findAll();
        List<HotelDTO> resHotel = new ArrayList<>();
        hotels.forEach(hotel -> {
            resHotel.add(HotelMapper.toDTO(hotel));
        });

        return resHotel;
    }
    public ResponseEntity<byte[]> getRoomImage( Long id) {
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

package working.hotellakewood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import working.hotellakewood.DTO.LocationDTO;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.entity.Location;
import working.hotellakewood.entity.User;
import working.hotellakewood.mapper.LocationMapper;
import working.hotellakewood.repository.HotelsRepository;
import working.hotellakewood.repository.LocationsRepository;
import working.hotellakewood.service.LocationService;
import working.hotellakewood.service.UserService;
import working.hotellakewood.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
@CrossOrigin("*")
public class LocationController {
    @Autowired
    public LocationsRepository lRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private LocationService locationService;
    @Autowired
    public HotelsRepository hRepo;
    @PostMapping("/add-location")
    public ResponseEntity<String> postLocation(@RequestBody LocationDTO locationDTO) {

        return ResponseEntity.ok(locationService.postLocation(locationDTO));
    }
    @GetMapping("")
    public ResponseEntity<List<LocationDTO>> getLocations() {
        return ResponseEntity.ok(locationService.getLocations());
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/forget/{emailId}")
    public ResponseEntity<String> forgetPassword(@PathVariable String emailId , @RequestBody User updatedUser){
        return ResponseEntity.ok().body(userService.updateUserPassword(emailId,updatedUser));
    }

//    @PutMapping("/update-location/{id}")
//    public ResponseEntity<Location> update(@PathVariable Long id,@RequestBody Location updated){
//        Location l=lRepo.findById(id).get();
//        l.getHotels().add(updated.getHotels().get(0));
//        updated.getHotels().get(0).setLocation(updated);
//
//    }

    @GetMapping("/location/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id) {
        Location l = lRepo.findById(id).get();

        l.getHotels().forEach(hotel -> {
            hotel.setLocation(null);
            String imageUrl = "http://localhost:9191/api/v1/hotels/hotel/" + hotel.getHotelId() + "/image";
            hotel.setImageUrl(imageUrl);
            hotel.setImageData(null);
            hotel.setRooms(null);

//            hotel.getRooms().forEach(room -> {
//                room.setImageData(null);
//                room.getRoomTypes().forEach(roomType -> roomType.setRooms(null));
//            });
        });
        return ResponseEntity.ok(l);

    }

    @GetMapping("hotels/{id}/image")
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

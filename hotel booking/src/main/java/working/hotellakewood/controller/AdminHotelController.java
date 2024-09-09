package working.hotellakewood.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.service.HotelService;
import working.hotellakewood.service.RoomService;

import java.io.IOException;

@RequestMapping("/api/v1/admin")
@RestController
@CrossOrigin("*")
public class AdminHotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService rService;
    @Transactional
    @PostMapping("/add-hotel")
    public ResponseEntity<String> addHotel(@RequestParam("file") MultipartFile file, @RequestParam("hotel") String hotelJson) throws IOException {
        return hotelService.addHotel(file, hotelJson);
    }

    @Transactional
    @PostMapping("/add-room")
    public ResponseEntity<String> addRoom(
            @RequestParam("file") MultipartFile file,
            @RequestParam("roomDto") String roomDtoJson) throws IOException {

        String response = rService.addRoom(file,roomDtoJson);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update-hotel/{id}")
    public ResponseEntity<Hotel> update(@PathVariable Long id, @RequestBody Hotel updated) {
        return hotelService.update(id, updated);
    }
}

package working.hotellakewood.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import working.hotellakewood.DTO.HotelDTO;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.mapper.HotelMapper;
import working.hotellakewood.repository.HotelsRepository;
import working.hotellakewood.service.HotelService;
import working.hotellakewood.utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/hotels")
@CrossOrigin("*")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelsRepository hRepo;


//    @Transactional
//    @PostMapping("/add-hotel")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("hotelDto") String hotelDtoJson) throws IOException {
//        return hotelService.addHotel(file, hotelDtoJson);
//    }
    @GetMapping("")
    public ResponseEntity<List<HotelDTO>> getRoomDetails() {
           return ResponseEntity.ok().body(hotelService.getAllHotels());
    }

    @GetMapping("/hotel-by-id/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @GetMapping("hotel/{id}/image")
    public ResponseEntity<byte[]> getRoomImage(@PathVariable Long id) {

        return hotelService.getRoomImage(id);
    }


}

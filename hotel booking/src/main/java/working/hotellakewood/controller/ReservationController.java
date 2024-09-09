package working.hotellakewood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import working.hotellakewood.DTO.ReservationDTO;
import working.hotellakewood.entity.Reservations;
import working.hotellakewood.entity.Room;
import working.hotellakewood.entity.User;
import working.hotellakewood.repository.ReservationRepository;
import working.hotellakewood.repository.RoomRepository;
import working.hotellakewood.repository.UserRepository;
import working.hotellakewood.service.ReservationService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/v1/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    @Autowired
    private ReservationService reservationService;
@PostMapping("/create/{roomId}/{userId}")

    public ResponseEntity<String> addReservation(@PathVariable Long roomId, @PathVariable Long userId, @RequestBody Reservations reservation){
    reservationService.reserve(roomId,userId,reservation);
    return ResponseEntity.ok().body("room is booked");

}


//    @GetMapping("/reservations")
//    public ResponseEntity<List<Reservations>> getAllReservations() {
//        List<Reservations> reservations = rRepo.findAll();
//        reservations.forEach(reservations1 -> {
//            reservations1.getRoom().setImageData(null);
//            String imageUrl = "http://localhost:9191/api/v1/room/" + reservations1.getRoom().getRoomId() + "/image";
//            reservations1.getRoom().setImageUrl(imageUrl);
//
////        reservations1.getRoom().getRoomTypes().forEach(roomType -> roomType.setRooms(null));
//            reservations1.getRoom().setReservations(null);
//            reservations1.getUser().setReservations(null);
//        });
//        return ResponseEntity.ok(reservations);
//
//    }

//@GetMapping("/reservations-by-user-id/{id}")
//    public ResponseEntity<List<Reservations>> getAllReservations(@PathVariable Long id){
//    List<Reservations> reservations = rRepo.findByUser(id);
//    reservations.forEach(reservations1 -> {
//        reservations1.getRoom().setImageData(null);
//        String imageUrl = "http://localhost:9191/api/v1/room/" + reservations1.getRoom().getRoomId() + "/image";
//        reservations1.getRoom().setImageUrl(imageUrl);
//
////        reservations1.getRoom().getRoomTypes().forEach(roomType -> roomType.setRooms(null));
//        reservations1.getRoom().setReservations(null);
//        reservations1.getUser().setReservations(null);
//    });
//    return ResponseEntity.ok(reservations);
//
//}

//    @GetMapping("/reservation/{code}")
//    public ResponseEntity<Reservations> getReservation(@PathVariable String code) {
//        Reservations reservation = rRepo.findByConfirmetionCode(code);
//        reservation.getRoom().getRoomTypes().forEach(roomType -> roomType.setRooms(null));
//        reservation.getRoom().setReservations(null);
//        String imageUrl = "http://localhost:9191/api/v1/room/" + reservation.getRoom().getRoomId() + "/image";
//        reservation.getRoom().setImageData(null);
//        reservation.getRoom().setImageUrl(imageUrl);
//        return ResponseEntity.ok(reservation);
//    }
//
//    @PutMapping("/update/reservation/{id}")
//    public ResponseEntity<?> updateReservationById(@PathVariable Long id, @RequestBody Reservations updatedReservation) {
//        User u = userRepo.findById(1L).get();
//        Reservations exsitingReservation = rRepo.findById(id).get();
//        exsitingReservation.setUserName(updatedReservation.getUserName());
//        exsitingReservation.setUser(u);
//        rRepo.save(exsitingReservation);
//        return ResponseEntity.ok(exsitingReservation);
//    }
}

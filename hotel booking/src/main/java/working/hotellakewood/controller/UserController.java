package working.hotellakewood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import working.hotellakewood.DTO.UserDTO;
import working.hotellakewood.entity.User;
import working.hotellakewood.repository.ReservationRepository;
import working.hotellakewood.repository.UserRepository;
import working.hotellakewood.service.UserService;

@RestController
@RequestMapping("/api/v3/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserRepository uRepo;
    @Autowired
    private ReservationRepository rRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;
    @GetMapping("/get-by-name/{gmailId}")
    public ResponseEntity<User> getByGmail(@PathVariable String gmailId) {
        User u = uRepo.findByEmail(gmailId).get();
        u.setReservations(null);


        return ResponseEntity.ok(u);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/forget/{emailId}")
    public ResponseEntity<String> forgetPassword(@PathVariable String id , @RequestBody User updatedUser){
        return ResponseEntity.ok().body(userService.updateUserPassword(id,updatedUser));
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody User updatedUser) {


        return ResponseEntity.ok(userService.updateUser(id,updatedUser));
    }

    @PutMapping("/delete-reservation-by-id/{userId}/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long userId, @PathVariable Long reservationId) {
        User user = uRepo.findById(userId).get();
        rRepo.deleteById(reservationId);
        return ResponseEntity.ok("reservation canceled");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUSer(@RequestBody User user) {
        uRepo.save(user);
        return ResponseEntity.ok(user);
    }
//    @DeleteMapping("delete"){
//
//    }
}

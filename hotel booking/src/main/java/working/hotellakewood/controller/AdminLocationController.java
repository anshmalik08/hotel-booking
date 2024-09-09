package working.hotellakewood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import working.hotellakewood.DTO.LocationDTO;
import working.hotellakewood.entity.Location;
import working.hotellakewood.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")
public class AdminLocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/add-location")
    public ResponseEntity<String> postLocation(@RequestBody LocationDTO locationDTO) {

        return ResponseEntity.ok(locationService.postLocation(locationDTO));
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.deleteById(id));
    }

    @GetMapping("/All-location")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return ResponseEntity.ok(locationService.getLocations());
    }
}

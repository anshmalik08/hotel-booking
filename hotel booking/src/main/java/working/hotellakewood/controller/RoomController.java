package working.hotellakewood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import working.hotellakewood.DTO.RoomDTO;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.entity.Room;

import working.hotellakewood.repository.HotelsRepository;
import working.hotellakewood.repository.RoomRepository;
import working.hotellakewood.service.RoomService;
import working.hotellakewood.utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("api/v1/rooms")
@CrossOrigin("*")

public class RoomController {

    @Autowired
    private RoomRepository rRepo;
    @Autowired
    private HotelsRepository hRepo;
    @Autowired
    private RoomService rService;




    @GetMapping("/room-by-id/{id}")
    public ResponseEntity<RoomDTO> getRoomByID(@PathVariable Long id) {

        return ResponseEntity.ok(rService.getRoomByID(id));
    }
//
//    @GetMapping("")
//    public ResponseEntity<List<Room>> getRoomDetails() {
//        List<Room> rooms = rRepo.findAll();
//
//        rooms.forEach(room -> {
//            String imageUrl = "http://localhost:9191/api/v1/rooms/room/" + room.getRoomId() + "/image";
//            room.setImageData(null);
//            room.setImageUrl(imageUrl);
//            room.setFileName(null);
//            room.setRoomTypes(null);
//            room.getHotel().setLocation(null);
//            room.getHotel().setRooms(null);
//            room.getHotel().setImageData(null);
//            String imageUrl1 = "http://localhost:9191/api/v1/hotels/hotel/" + room.getHotel().getHotelId() + "/image";
//            room.getHotel().setImageUrl(imageUrl1);
//
//            room.setReservations(null);
////           System.out.println("the value is : "+room.getTypes().get(0).getType());
//        });
//        return ResponseEntity.ok(rooms);
//    }
//
    @GetMapping("room/{id}/image")
    public ResponseEntity<byte[]> getRoomImage(@PathVariable Long id) {
        Room room = rRepo.findById(id).orElse(null);

        if (room == null || room.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }

        MediaType mediaType = switch (room.getFileName().substring(room.getFileName().lastIndexOf('.') + 1).toLowerCase()) {
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "pdf" -> MediaType.APPLICATION_PDF;

            // Add more cases for other image formats if needed
            default -> MediaType.APPLICATION_OCTET_STREAM; // Default to binary data if the type is unknown
        };
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType); // adjust based on your image type

        return new ResponseEntity<>(ImageUtils.decompressImage(room.getImageData()), headers, HttpStatus.OK);
    }
//
////    @GetMapping("/types")
////    public ResponseEntity<List<RoomType>> getAllTypes() {
////        List<RoomType> roomTypes = rTypeRepo.findAll();
////        roomTypes.forEach(roomType -> {
////            System.out.println("deleting");
////            roomType.getRooms().forEach(room -> {
////                room.setRoomTypes(null);
////                room.setImageData(null);
////            });
////
////        });
////        return ResponseEntity.ok(roomTypes);
////    }
//
////    @PutMapping("/update-room/{id}")
////
////    public ResponseEntity<?> updateRoomById(@PathVariable Long id, @RequestParam("file") MultipartFile file,
////                                            @RequestParam("room") String room) throws IOException {
////        ObjectMapper ob = new ObjectMapper();
////        Room updatedRoom = ob.readValue(room, Room.class);
////
////        Room existingRoom = rRepo.findById(id).get();
////
////        existingRoom.setImageData(ImageUtils.compressImage(file.getBytes()));
////        existingRoom.setFileName(file.getOriginalFilename());
////        existingRoom.setDescription(updatedRoom.getDescription());
////        existingRoom.setPrice(updatedRoom.getPrice());
////        existingRoom.setRoomTypes(existingRoom.getRoomTypes());
////        rRepo.save(existingRoom);
////        return ResponseEntity.ok(existingRoom);
////    }
////
////    @PutMapping("/update-roomtype/type/{id}")
////    public ResponseEntity<?> updateRoomType(@PathVariable Long id, @RequestBody List<RoomType> roomTypes) {
////        Room room = rRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Room not found"));
////
////        // Use iterator to safely remove elements from the list
////        Iterator<RoomType> iterator = room.getRoomTypes().iterator();
////        while (iterator.hasNext()) {
////            RoomType existingRoomType = iterator.next();
////            Iterator<Room> roomIterator = existingRoomType.getRooms().iterator();
////
////            while (roomIterator.hasNext()) {
////                Room roomToRemove = roomIterator.next();
////                if (roomToRemove.getRoomId() == id) {
////                    roomIterator.remove();
////                }
////            }
////
////            // Remove the RoomType from the list if it has no more associated rooms
////            if (existingRoomType.getRooms().isEmpty()) {
////                iterator.remove();
////            }
////        }
////
////        // Create a new list to store the updated RoomType objects
////        List<RoomType> newRoomTypes = new ArrayList<>();
////
////        for (RoomType inputRoomType : roomTypes) {
////            RoomType existingRoomType = rTypeRepo.findByRoomType(inputRoomType.getRoomType());
////
////            if (existingRoomType == null) {
////                // Create a new RoomType if it doesn't exist
////                existingRoomType = new RoomType();
////                existingRoomType.setRoomType(inputRoomType.getRoomType());
////                rTypeRepo.save(existingRoomType);
////            }
////
////            newRoomTypes.add(existingRoomType);
////        }
////
////        room.setRoomTypes(newRoomTypes);
////        rRepo.save(room);
////
////        return ResponseEntity.ok(room);
////    }
////
////    @PutMapping("/update-roomtype/{id}")
////
////    public ResponseEntity<?> updateType(@PathVariable Long id, @RequestBody RoomType updatedRoomType) {
////
////        RoomType roomType = rTypeRepo.findById(id).get();
////
////        roomType.setRoomType(updatedRoomType.getRoomType());
////        rTypeRepo.save(roomType);
////        roomType.setRooms(roomType.getRooms());
////        return ResponseEntity.ok(roomType);
////    }
////
////    @DeleteMapping("/delete/room/{id}")
////    public ResponseEntity<Room> deleteRoomById(@PathVariable Long id) {
////        Room roomToDelete = rRepo.findById(id).get();
////        for (RoomType r : roomToDelete.getRoomTypes()
////        ) {
////            r.getRooms().remove(roomToDelete);
////            rTypeRepo.save(r);
////        }
////        rRepo.deleteById(id);
////        return ResponseEntity.ok(roomToDelete);
////    }
////
////    @DeleteMapping("/delete/roomtype/{id}")
////    public ResponseEntity<RoomType> deleteRoomType(@PathVariable Long id) {
////        RoomType roomTypeToDelete = rTypeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("RoomType not found"));
////
////        for (Room room : roomTypeToDelete.getRooms()) {
////            // Remove the RoomType from the rooms collection without triggering cascade delete
////            room.getRoomTypes().remove(roomTypeToDelete);
////            rRepo.save(room);
////        }
////
////        // Delete the RoomType
////        rTypeRepo.deleteById(id);
////
////        return ResponseEntity.ok(roomTypeToDelete);
////    }
////
////    @DeleteMapping("/delete")
////    public String del() {
////        rRepo.deleteAll();
////        rTypeRepo.deleteAll();
////        return "all deleted";
////    }
}

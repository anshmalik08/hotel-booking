package working.hotellakewood.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import working.hotellakewood.DTO.RoomDTO;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.entity.Room;
import working.hotellakewood.mapper.RoomMapper;
import working.hotellakewood.repository.HotelsRepository;
import working.hotellakewood.repository.RoomRepository;
import working.hotellakewood.utils.ImageUtils;

import java.io.IOException;

@Service
public class RoomService {

    @Autowired
    private HotelsRepository hRepo;

    @Autowired
    private RoomRepository rRepo;

    public String addRoom(
           MultipartFile file,
           String roomDtoJson) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        RoomDTO roomDTO = objectMapper.readValue(roomDtoJson, RoomDTO.class);
        roomDTO.setImageData(ImageUtils.compressImage(file.getBytes()));
        roomDTO.setFileName(file.getOriginalFilename());
        Room room = RoomMapper.toEntity(roomDTO);
        Hotel hotel = hRepo.findById(room.getHotel().getHotelId()).orElse(null);

        if (hotel != null) {
            hotel.getRooms().add(room);
            room.setHotel(hotel);
        } else {
            return "Hotel nt Found";
        }

        rRepo.save(room);
        hRepo.save(hotel);

        return "room added Successfully";
    }

    public RoomDTO getRoomByID( Long id) {
        Room room = rRepo.findById(id).get();
        RoomDTO roomDTO = RoomMapper.toDtoForHotel(room);
        System.out.println("setting image data is null");
        roomDTO.setImageData(null);
        System.out.println(" image data is null has been set");

        roomDTO.setReservationDTOS(null);
        roomDTO.getHotelDTO().setRooms(null);
        roomDTO.getHotelDTO().setImageData(null);
        roomDTO.getHotelDTO().getLocationDTO().setHotelsDTO(null);
        roomDTO.getHotelDTO().setImageData(null);

        return (roomDTO);
    }


}

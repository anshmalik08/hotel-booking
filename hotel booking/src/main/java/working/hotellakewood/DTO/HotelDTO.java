package working.hotellakewood.DTO;


import lombok.Getter;
import lombok.Setter;
import working.hotellakewood.entity.Location;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HotelDTO {

    private Long hotelId;
    private String hotelName;
    private Float rating;
    private String address;

    private  List<RoomDTO> rooms = new ArrayList<>();

    private byte[] imageData;
    private String fileName;

    private String imageUrl;

    private LocationDTO locationDTO;
}
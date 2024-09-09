package working.hotellakewood.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class LocationDTO {

    private Long locationId;
    private String locationName;
    private  List<HotelDTO> hotelsDTO = new ArrayList<>();
}

package working.hotellakewood.DTO;

import lombok.Getter;
import lombok.Setter;
import working.hotellakewood.entity.Hotel;
import working.hotellakewood.entity.Reservations;


import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class RoomDTO {
        private Long roomId;
        private Long roomNo;
        private String description;
        private Long price;
        private String fileName;

        private byte[] imageData;

        private String imageUrl;

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        private String roomType ;

        private HotelDTO hotelDTO;

        private List<ReservationDTO> reservationDTOS = new ArrayList<>();
    }

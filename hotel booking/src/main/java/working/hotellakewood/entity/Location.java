package working.hotellakewood.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "location_db")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    @Column(unique = true)
    private String locationName;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Hotel> hotels = new ArrayList<>();
}

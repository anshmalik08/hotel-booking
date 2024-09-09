package working.hotellakewood.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@CrossOrigin("*")
public class demo {
    @GetMapping("/d")
    public String demoo(){
        return "hlo";
    }
}

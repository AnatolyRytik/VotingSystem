package topjava.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.RestaurantService;
import topjava.graduation.util.exception.NotFoundException;

import java.util.List;

@RequestMapping(value = RootController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RootController {

    public static final String REST_URL = "/rest/restaurants";
    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("get all restaurants");
        return new ResponseEntity<>(restaurantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public Restaurant findByName(@RequestParam("name") String name) {
        log.info("find restaurant by name ={}", name);
        return restaurantService.findByName(name).orElseThrow(() -> new NotFoundException(
                String.format("Restaurant with name %s not found", name)));
    }
}

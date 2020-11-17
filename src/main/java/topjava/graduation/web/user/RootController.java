package topjava.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.RestaurantService;


import java.util.List;

@RequestMapping(value = RootController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RootController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

    public static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        log.info("get all restaurants");
        return new ResponseEntity<>(restaurantService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Restaurant>> findByName(@RequestParam("name") String name) {
        log.info("find restaurants by name ={}", name);
        return new ResponseEntity<>(restaurantService.findByName(name), HttpStatus.OK);
    }
}

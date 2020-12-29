package topjava.graduation.web.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.RestaurantService;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = RestaurantUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class RestaurantUserController {
    public static final String REST_URL = "/rest/restaurants";
    private static final Logger log = LoggerFactory.getLogger(RestaurantUserController.class);
    private final RestaurantService restaurantService;

    @GetMapping("/name")
    public Restaurant findByName(@RequestParam("name") String name) {
        log.info("find restaurant by name ={}", name);
        return restaurantService.findByName(name);
    }

    @GetMapping("/with-dishes")
    public List<Restaurant> getAllWithDishesByDate() {
        log.info("list of restaurants with dishes for today");
        return restaurantService.getAllTodayRestaurantsWithDishes(LocalDate.now());
    }
}

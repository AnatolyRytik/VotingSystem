package topjava.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.RestaurantService;


import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = RootController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RootController {

    private static Logger log = LoggerFactory.getLogger(RestaurantService.class);

    public static final String REST_URL = "/rest/restaurants";

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/dishes")
    public ResponseEntity<List<Restaurant>> getAllRestaurantWithDishesByDate(
            @RequestParam(value = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get list of restaurants with dishes by date: {}", date);
        LocalDate useDate = date;
        if (useDate == null) useDate = LocalDate.now();
        return new ResponseEntity<>(restaurantService.getAllRestaurantWithDishesByDate(useDate), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/detail")
    public ResponseEntity<Restaurant> getRestaurantWithDishesByDate(@PathVariable("id") long id,
                                                                    @RequestParam(value = "date", required = false)
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get restaurant with dishes by date: {}", date);
        LocalDate useDate = date;
        if (useDate == null) useDate = LocalDate.now();
        return new ResponseEntity<>(restaurantService.getRestaurantWithDishesByDate(id, useDate), HttpStatus.OK);
    }
}

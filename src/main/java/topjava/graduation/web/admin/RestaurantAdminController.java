package topjava.graduation.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.RestaurantService;
import topjava.graduation.util.exception.NotFoundException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantAdminController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantAdminController.class);
    public static final String REST_URL = "/rest/admin/restaurants";

    @Autowired
    private RestaurantService service;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll(){
        log.info("get all restaurants {}");
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable("id") long id) throws NotFoundException {
        log.info("get restaurant with id {}", id);
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        log.info("delete restaurant by id {} ", id);
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Restaurant created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant newRestaurant, @PathVariable("id") long id) {
        log.info("update restaurant{} with id {}", newRestaurant, id);
        service.update(newRestaurant, id);
    }
}

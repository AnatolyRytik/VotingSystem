package topjava.graduation.web.admin;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.RestaurantService;
import topjava.graduation.to.RestaurantTo;
import topjava.graduation.util.exception.NotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static topjava.graduation.util.RestaurantUtil.*;
import static topjava.graduation.util.ValidationUtil.assureIdConsistent;


@RequestMapping(value = RestaurantAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class RestaurantAdminController {
    public static final String REST_URL = "/rest/admin/restaurants";
    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);
    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("get all restaurants");
        return createListToFromListEntity(restaurantService.getAll());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) throws NotFoundException {
        log.info("delete restaurant by id {}", id);
        restaurantService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestaurantTo> create(@Valid @RequestBody RestaurantTo restaurant) {
        log.info("create restaurant {}", restaurant);
        RestaurantTo created = asTo(restaurantService.create(createNewFromTo(restaurant)));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/{id}")
    public RestaurantTo getById(@PathVariable("id") long id) throws NotFoundException {
        log.info("get restaurant by id: {}", id);
        return asTo(restaurantService.getById(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public RestaurantTo update(@Valid @RequestBody RestaurantTo restaurantTo, @PathVariable("id") int id) throws NotFoundException {
        log.info("update restaurant{} with id {}", restaurantTo, id);
        Restaurant restaurant = createNewFromTo(restaurantTo);
        assureIdConsistent(restaurant, id);
        return asTo(restaurantService.update(restaurant, id));
    }
}
package topjava.graduation.web.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import topjava.graduation.model.Dish;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.DishService;
import topjava.graduation.service.RestaurantService;
import topjava.graduation.to.DishTo;
import topjava.graduation.util.exception.NotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static topjava.graduation.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = DishAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminController {
    private static final Logger log = LoggerFactory.getLogger(DishAdminController.class);
    public static final String REST_URL = "/rest/admin/dishes";

    @Autowired
    private DishService dishService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Dish>> getAll() {
        log.info("get all dishes");
        return new ResponseEntity<>(dishService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Dish getById(@PathVariable("id") long id) throws NotFoundException {
        log.info("get dish with id {}", id);
        return dishService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) throws NotFoundException {
        log.info("delete dish with id {} ", id);
        dishService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dish> create(@Valid @RequestBody DishTo dishTo) {
        log.info("create dish {}", dishTo);
        Restaurant restaurant = restaurantService.getById(dishTo.getRestaurantId());
        Dish dish = new Dish(dishTo.getName(), dishTo.getPrice(), restaurant, dishTo.getDate());
        Dish created = dishService.create(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Dish update(@Valid @RequestBody DishTo dishTo, @PathVariable("id") long id) throws NotFoundException {
        log.info("update dish {} with id {}", dishTo, id);
        assureIdConsistent(dishTo, id);
        Restaurant restaurant = restaurantService.getById(dishTo.getRestaurantId());
        Dish dish = new Dish(dishTo.getName(), dishTo.getPrice(), restaurant, dishTo.getDate());
        return dishService.update(dish, id);
    }
}

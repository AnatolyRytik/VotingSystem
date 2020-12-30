package topjava.graduation.web.admin;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import static topjava.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishAdminController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DishAdminController {
    public static final String REST_URL = "/rest/admin/dishes";
    private static final Logger log = LoggerFactory.getLogger(DishAdminController.class);
    private final DishService dishService;
    private final RestaurantService restaurantService;

    @GetMapping
    public List<Dish> getAll() {
        log.info("get all dishes");
        return dishService.getAll();
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
        Restaurant restaurant = restaurantService.getOneByDish(dishTo);
        Dish dish = new Dish(dishTo.getName(), dishTo.getPrice(), restaurant, dishTo.getDate());
        checkNew(dish);
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
        Restaurant restaurant = restaurantService.getOneByDish(dishTo);
        Dish dish = new Dish(dishTo.getName(), dishTo.getPrice(), restaurant, dishTo.getDate());
        assureIdConsistent(dish, id);
        return dishService.update(dish, id);
    }
}

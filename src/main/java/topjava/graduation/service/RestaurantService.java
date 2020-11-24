package topjava.graduation.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import topjava.graduation.model.Restaurant;
import topjava.graduation.repository.RestaurantRepository;
import topjava.graduation.util.exception.NotFoundException;

import java.util.List;

import static topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public Restaurant getById(long id) throws NotFoundException {
        log.info("restaurant with id {}", id);
        return checkNotFoundWithId(restaurantRepository.getById(id), id);
    }

    public List<Restaurant> findByName(String name) {
        log.info("find restaurants by name ={}", name);
        return restaurantRepository.findByNameIgnoreCase(name);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.findAll();
    }


    public void delete(long id) {
        log.info("delete restaurant with id {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public Restaurant update(Restaurant restaurant, long id) {
        log.info("update restaurant {} with id {}", restaurant, id);
        Assert.notNull(restaurant, "restaurant must not be null");
        Restaurant rest = getById(id);
        rest.setName(restaurant.getName());
        return restaurantRepository.save(rest);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }
}
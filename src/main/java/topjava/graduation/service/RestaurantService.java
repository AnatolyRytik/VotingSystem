package topjava.graduation.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import topjava.graduation.model.Restaurant;
import topjava.graduation.repository.RestaurantRepository;
import topjava.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"restaurant", "restaurant_with_dishes"})
public class RestaurantService {
    private static final Logger log = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private final RestaurantRepository restaurantRepository;

    public Restaurant getById(long id) throws NotFoundException {
        log.info("restaurant with id {}", id);
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    public Optional<Restaurant> findByName(String name) {
        log.info("find restaurants by name ={}", name);
        return restaurantRepository.findByNameIgnoreCase(name);
    }

    @Cacheable("restaurant")
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.findAll();
    }

    @CacheEvict(value = {"restaurant", "restaurant_with_dishes"}, allEntries = true)
    public void delete(long id) throws NotFoundException {
        log.info("delete restaurant with id {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @CacheEvict(value = {"restaurant", "restaurant_with_dishes"}, allEntries = true)
    public Restaurant update(Restaurant restaurant, long id) throws NotFoundException {
        log.info("update restaurant {} with id {}", restaurant, id);
        Assert.notNull(restaurant, "restaurant must not be null");
        Restaurant rest = getById(id);
        rest.setName(restaurant.getName());
        return checkNotFoundWithId(restaurantRepository.save(rest), id);
    }

    @CacheEvict(value = {"restaurant", "restaurant_with_dishes"}, allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @Cacheable("restaurant_with_dishes")
    public List<Restaurant> getAllTodayRestaurantsWithDishes(LocalDate date) {
        log.info("get list of restaurants with dishes for today");
        return restaurantRepository.getAllTodayRestaurantsWithDishes(date);
    }
}
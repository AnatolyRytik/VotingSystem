package topjava.graduation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topjava.graduation.model.Restaurant;
import topjava.graduation.repository.RestaurantRepository;
import topjava.graduation.util.exception.NotFoundException;

import java.util.List;

import static topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private static Logger log = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository repository;

    public Restaurant getById(long id) throws NotFoundException {
        log.info("restaurant with id {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public List<Restaurant> getByName(String name) {
        log.info("find restaurants by title ={}", name);
        return repository.getByName(name);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants service {}");
        return repository.getAll();
    }

    public List<Restaurant> getRestaurantsWithLunches() {
        log.info("get all restaurants with lunches {}");
        return repository.getRestaurantsWithLunches();
    }

    public void delete(long id) {
        log.info("delete restaurant with id {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }


    public void update(Restaurant restaurant, long id) {
        log.info("update restaurant {} with id {}", restaurant, id);
        checkNotFoundWithId(repository.save(restaurant), id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        return repository.save(restaurant);
    }

}


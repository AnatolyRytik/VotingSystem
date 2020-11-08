package topjava.graduation.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.Restaurant;
import topjava.graduation.repository.RestaurantRepository;
import topjava.graduation.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private static Logger log = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public Restaurant getById(long id) throws NotFoundException {
        log.info("restaurant with id {}", id);
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @Transactional(readOnly = true)
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Restaurant> getAllRestaurantWithDishesByDate(final LocalDate date) {
        log.info("Get list of restaurants with dishes by date: {}", date);
        return restaurantRepository.getAllRestaurantWithDishesByDate(date);
    }

    @Transactional(readOnly = true)
    public Restaurant getRestaurantWithDishesByDate(long id, LocalDate date) {
        return restaurantRepository.getRestaurantWithDishesByDate(id, date)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Restaurant with id %s not found", id)));
    }

    @Transactional
    public void delete(long id) {
        log.info("delete restaurant with id {}", id);
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Transactional
    public void update(Restaurant restaurant, long id) {
        log.info("update restaurant {} with id {}", restaurant, id);
        checkNotFoundWithId(restaurantRepository.save(restaurant), id);
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        log.info("create restaurant {}", restaurant);
        return restaurantRepository.save(restaurant);
    }
}
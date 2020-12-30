package topjava.graduation.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import topjava.graduation.model.Dish;
import topjava.graduation.repository.DishRepository;
import topjava.graduation.util.exception.NotFoundException;

import java.util.List;

import static topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@RequiredArgsConstructor
public class DishService {
    private static final Logger log = LoggerFactory.getLogger(DishService.class);
    private final DishRepository dishRepository;

    @Transactional(readOnly = true)
    public List<Dish> getAll() {
        log.info("get all dishes");
        return dishRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Dish getById(long id) {
        log.info("get dish with id {}", id);
        return dishRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ("Dish not found")));
    }

    @Transactional
    @CacheEvict(value = {"today_restaurants_with_dishes"}, allEntries = true)
    public void delete(long id) {
        log.info("delete dish with id {}", id);
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    @Transactional
    @CacheEvict(value = {"today_restaurants_with_dishes"}, allEntries = true)
    public Dish create(Dish newDish) {
        log.info("create dish {}", newDish);
        Assert.notNull(newDish, "dish must not be null");
        return dishRepository.save(newDish);
    }

    @Transactional
    @CacheEvict(value = {"today_restaurants_with_dishes"}, allEntries = true)
    public Dish update(Dish newDish, long id) {
        log.info("update dish {} with id {}", newDish, id);
        Assert.notNull(newDish, "dish must not be null");
        return checkNotFoundWithId(dishRepository.save(newDish), id);
    }
}
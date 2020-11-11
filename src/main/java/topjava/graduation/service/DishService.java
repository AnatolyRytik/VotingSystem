package topjava.graduation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topjava.graduation.repository.DishRepository;
import topjava.graduation.model.Dish;
import topjava.graduation.util.exception.NotFoundException;

import java.util.List;

import static topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private static Logger log = LoggerFactory.getLogger(DishService.class);

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAll() {
        log.info("get all dishes {}");
        return dishRepository.findAll();
    }

    public Dish getById(long id) throws NotFoundException {
        log.info("get dish with id {}", id);
        return checkNotFoundWithId(dishRepository.findById(id).orElse(null), id);
    }

    public void delete(long id) throws NotFoundException {
        log.info("delete dish with id {}", id);
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    public Dish create(Dish newDish) {
        log.info("create dish {}", newDish);
        return dishRepository.save(newDish);
    }

    public void update(Dish newDish, long id) throws NotFoundException {
        log.info("update dish {} with id {}", newDish, id);
        checkNotFoundWithId(dishRepository.save(newDish), id);
    }
}
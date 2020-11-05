package topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import topjava.graduation.model.Restaurant;
import topjava.graduation.repository.RestaurantRepository;
import topjava.graduation.util.exception.NotFoundException;

import java.util.List;

import static topjava.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    public Restaurant getById(long id) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public List<Restaurant> getByName(String name) {
        return repository.getByName(name);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public List<Restaurant> getRestaurantsWithLunches() {
        return repository.getRestaurantsWithLunches();
    }

    public void delete(long id) {
        checkNotFoundWithId(repository.delete(id), id);
    }


    public void update(Restaurant restaurant, int id) {
        checkNotFoundWithId(repository.save(restaurant), id);
    }

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

}


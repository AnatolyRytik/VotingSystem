package topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(long id);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.dishes WHERE r.name=:name")
    Optional<Restaurant> findByNameIgnoreCase(String name);

    @Transactional
    @Override
    Restaurant save(Restaurant restaurant);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.dishes d WHERE d.date = ?1 ORDER BY r.name")
    List<Restaurant> getAllTodayRestaurantsWithDishes(LocalDate date);
}

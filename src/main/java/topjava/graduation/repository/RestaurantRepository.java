package topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") long id);


    Optional<Restaurant> findByNameIgnoreCase(String name);

    @Transactional
    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant getById(@Param("id") long id);

    @Transactional
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.dishes ORDER BY r.name")
    List<Restaurant> getAllRestaurantsWithDishes();

    @Transactional
    @Override
    Restaurant save(Restaurant restaurant);

}

package topjava.graduation.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.RestaurantService;

import java.util.List;

@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r ORDER BY r.name")
    List<Restaurant> getAll();

    @Transactional
    @Override
    Restaurant save(Restaurant restaurant);

    @Modifying
    @Transactional
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") long id);

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE CONCAT('%', :name, '%')")
    List<Restaurant> getByName(@Param("name") String name);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.lunches ORDER BY r.name")
    List<Restaurant> getRestaurantsWithLunches();
}

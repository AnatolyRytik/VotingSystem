package topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.lunches d WHERE d.date =:date ORDER BY r.name")
    List<Restaurant> getAllRestaurantWithLunchesByDate(@Param("date") LocalDate date);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.lunches d WHERE d.date =:date and r.id=:id")
    Optional<Restaurant> getRestaurantWithLunchesByDate(@Param("id") long id, @Param("date") LocalDate date);

    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") long id);

    Optional<Restaurant> findByName(String name);

}

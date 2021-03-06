package topjava.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Override
    @Transactional
    Vote save(Vote vote);

    Integer countAllByRestaurantIdAndDate(long restaurant_id, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    Optional<Vote> getByUserIdAndDate(Long id, LocalDate date);

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(long id);
}

package topjava.graduation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.Restaurant;
import topjava.graduation.model.Vote;
import topjava.graduation.repository.RestaurantRepository;
import topjava.graduation.repository.UserRepository;
import topjava.graduation.repository.VoteRepository;
import topjava.graduation.util.exception.DeadLineException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


@Service
public class VoteService {
    private static final Logger log = LoggerFactory.getLogger(VoteService.class);
    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalTime TIME_LIMIT = LocalTime.parse("11:00");
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }


    public Integer getVotesCountByDate(long restaurantId, LocalDate date) {
        log.info("get votes for restaurant with id ={} by date ={}", restaurantId, date);
        return voteRepository.countAllByRestaurantIdAndDate(restaurantId, date);
    }

    public Optional<Vote> getTodayUserVote(long userId, LocalDate date) {
        log.info("get user with id ={} today={} vote", userId, date);
        return voteRepository.getTodayUserVote(userId, date);
    }

    @Transactional
    public void createOrUpdateVote(final long restaurantId,
                         final LocalTime time, final long userId) {
        log.debug("Make user vote, restaurant id: {},current time {},  deadline is {}",
                restaurantId, time, TIME_LIMIT);
        Vote vote;
        Restaurant restaurant = restaurantRepository.getById(restaurantId);
        Optional<Vote> optionalVote = voteRepository.getTodayUserVote(userId, TODAY);
        if (optionalVote.isPresent()) {
            log.debug("User has already voted");
            if (time.isAfter(TIME_LIMIT)) {
                throw new DeadLineException("It is not possible to change your vote after 11.00 AM!");
            }
            Vote oldVote = optionalVote.get();
            oldVote.setRestaurant(restaurant);
            vote = oldVote;
        } else {
            log.debug("User's first vote for today");
            vote = new Vote(userRepository.getOne(userId), restaurant, TODAY);
        }
        voteRepository.save(vote);
    }
}

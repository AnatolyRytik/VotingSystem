package topjava.graduation.service;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topjava.graduation.model.Vote;
import topjava.graduation.repository.RestaurantRepository;
import topjava.graduation.repository.UserRepository;
import topjava.graduation.repository.VoteRepository;
import topjava.graduation.to.VoteTo;


@Service
public class VoteService {
    private static final Logger log = LoggerFactory.getLogger(VoteService.class);
    private static final LocalDate TODAY = LocalDate.now();
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
    public VoteTo create(long userId, long restaurantId) {
        Vote todayVote = getTodayUserVote(userId, TODAY).orElse(null);
        if (todayVote != null) {
            throw new DataIntegrityViolationException("");
        }
        Vote newVote = new Vote(userRepository.getOne(userId),
                restaurantRepository.getOne(restaurantId), TODAY);
        VoteTo voteTo = new VoteTo(newVote, true);
        voteRepository.save(voteTo.getVote());
        log.info("create vote{} ", voteTo);
        return voteTo;
    }

    @Transactional
    public VoteTo createOrUpdate(long userId, long restaurantId) {
        Vote newVote = new Vote(userRepository.getOne(userId),
                restaurantRepository.getOne(restaurantId), TODAY);
        VoteTo todayVote = voteRepository.getTodayUserVote(userId, TODAY)
                .map(v -> {
                    v.setRestaurant(restaurantRepository.getOne(restaurantId));
                    return new VoteTo(v, false);
                })
                .orElseGet(() -> new VoteTo(newVote, true));
        voteRepository.save(todayVote.getVote());
        log.info("update vote{}", todayVote);
        return todayVote;
    }
}

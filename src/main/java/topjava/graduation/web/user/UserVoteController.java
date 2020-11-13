package topjava.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import topjava.graduation.model.Restaurant;
import topjava.graduation.model.Vote;
import topjava.graduation.service.VoteService;
import topjava.graduation.to.VoteTo;
import topjava.graduation.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;


@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {
    private static final Logger log = LoggerFactory.getLogger(UserVoteController.class);
    public static final String REST_URL = "/rest/vote";
    private static final LocalTime TIME_LIMIT = LocalTime.parse("11:00");

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/{id}")
    public ResponseEntity<Restaurant> vote(@PathVariable("id") long restaurantId) {
        long userId = SecurityUtil.authUserId();
        boolean acceptVote = LocalTime.now().isBefore(TIME_LIMIT);
        VoteTo newVote = acceptVote ? voteService.createOrUpdate(userId, restaurantId)
                : voteService.create(userId, restaurantId);
        log.info("user with {} id gave vote for restaurant {}", userId, restaurantId);
        return new ResponseEntity<>(newVote.getVote().getRestaurant(),
                newVote.isCreated() ? HttpStatus.CREATED : (acceptVote ? HttpStatus.OK : HttpStatus.CONFLICT));
    }

    @GetMapping
    public Vote getUserVoteForDate(
            @RequestParam(value = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote for user with id ={}, day ={}", userId, date);
        return voteService.getTodayUserVote(userId, date).get();
    }

    @GetMapping(value = "/votes-by-date/{id}")
    public Integer getVotesCountByDate(@RequestParam(value = "date")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                       @PathVariable("id") long restaurantId) {
        log.info("get votes for restaurant with id ={} by date ={}", restaurantId, date);
        return voteService.getVotesCountByDate(restaurantId, date);
    }
}

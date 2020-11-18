package topjava.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import topjava.graduation.model.Vote;
import topjava.graduation.service.VoteService;
import topjava.graduation.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;


@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserVoteController {
    private static final Logger log = LoggerFactory.getLogger(UserVoteController.class);
    public static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteService voteService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/{id}")
    public void vote(@PathVariable("id") long restaurantId) {
        long userId = SecurityUtil.authUserId();
        voteService.createOrUpdateVote(restaurantId, LocalTime.now(), userId);
    }

    @GetMapping
    public Vote getUserVoteForDate(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        int userId = SecurityUtil.authUserId();
        log.info("get vote for user with id ={}, day ={}", userId, date);
        return voteService.getTodayUserVote(userId, date).orElse(null);
    }

    @GetMapping(value = "/votes-by-date/{id}")
    public Integer getVotesCountByDate(@RequestParam(value = "date")
                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                       @PathVariable("id") long restaurantId) {
        log.info("get votes for restaurant with id ={} by date ={}", restaurantId, date);
        return voteService.getVotesCountByDate(restaurantId, date);
    }
}

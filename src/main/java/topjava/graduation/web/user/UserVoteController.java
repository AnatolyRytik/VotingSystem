package topjava.graduation.web.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import topjava.graduation.model.Vote;
import topjava.graduation.service.VoteService;
import topjava.graduation.web.AuthUser;

import java.time.LocalDate;
import java.time.LocalTime;


@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserVoteController {
    public static final String REST_URL = "/rest/votes";
    private static final Logger log = LoggerFactory.getLogger(UserVoteController.class);
    private final VoteService voteService;

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping
    public void vote(@RequestParam("restaurant_id") long restaurantId, @AuthenticationPrincipal AuthUser authUser) {
        log.info("vote of user with id ={}", authUser.id());
        voteService.createOrUpdateVote(restaurantId, LocalTime.now(), authUser.id());
    }

    @GetMapping
    public Vote getUserVoteForDate(
            @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @AuthenticationPrincipal AuthUser authUser) {
        long userId = authUser.id();
        log.info("get vote for user with id ={}, day ={}", userId, date);
        return voteService.getUserVoteForDate(userId, date);
    }
}

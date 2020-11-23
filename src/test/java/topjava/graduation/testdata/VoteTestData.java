package topjava.graduation.testdata;

import topjava.graduation.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static topjava.graduation.model.AbstractBaseEntity.START_SEQ;
import static topjava.graduation.testdata.RestaurantTestData.RESTAURANT_1;
import static topjava.graduation.testdata.RestaurantTestData.RESTAURANT_2;
import static topjava.graduation.testdata.UserTestData.USER_0;
import static topjava.graduation.testdata.UserTestData.USER_1;

public class VoteTestData {
    public static final long VOTE_ID = START_SEQ;

    public static final Vote VOTE_1 = new Vote(VOTE_ID, USER_0, RESTAURANT_1, LocalDate.now());
    public static final Vote VOTE_2 = new Vote(VOTE_ID + 1, USER_1, RESTAURANT_2, LocalDate.now());
    public static final List<Vote> VOTE_HISTORY_USER_1 = Arrays.asList(VOTE_2);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}

package topjava.graduation.testdata;

import topjava.graduation.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static topjava.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final long RES_ID = START_SEQ;

    public static final Restaurant RESTAURANT_0 = new Restaurant(RES_ID, "Subway");
    public static final Restaurant RESTAURANT_1 = new Restaurant(RES_ID + 1, "KFC");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RES_ID + 2, "Burger King");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_0, RESTAURANT_1, RESTAURANT_2);

    public static Restaurant getUpdated() {
        return new Restaurant(RES_ID, RESTAURANT_1.getName());
    }

    public static Restaurant getCreated() {
        return new Restaurant(null, "Starbucks");
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}

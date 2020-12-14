package topjava.graduation.testdata;

import topjava.graduation.TestMatcher;
import topjava.graduation.model.Restaurant;

import java.util.Arrays;
import java.util.List;


public class RestaurantTestData {
    public static final long RES_ID = 0L;
    public static final Restaurant RESTAURANT_0 = new Restaurant(RES_ID, "MacDonalds");
    public static final Restaurant RESTAURANT_1 = new Restaurant(RES_ID + 1, "KFC");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RES_ID + 2, "Burger King");
    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT_0, RESTAURANT_1, RESTAURANT_2);
    public static final List<Restaurant> TODAY_RESTAURANTS = Arrays.asList(RESTAURANT_0, RESTAURANT_1);
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Restaurant.class, "dishes");

    public static Restaurant getUpdated() {
        return new Restaurant(RES_ID, "UpdatedName");
    }

    public static Restaurant getCreated() {
        return new Restaurant(null, "Starbucks");
    }
}

package topjava.graduation.testdata;

import topjava.graduation.TestMatcher;
import topjava.graduation.model.Dish;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static topjava.graduation.testdata.RestaurantTestData.*;

public class DishTestData {
    public static final long DISH_ID = 0L;
    //RESTAURANT_0
    public static final Dish DISH_0 = new Dish(DISH_ID, LocalDate.now(), "Bigmac", 300.0, RESTAURANT_0);
    public static final Dish DISH_1 = new Dish(DISH_ID + 1, LocalDate.now(), "Coca cola", 100.0, RESTAURANT_0);
    //RESTAURANT_1
    public static final Dish DISH_2 = new Dish(DISH_ID + 2, LocalDate.now(), "Chicken nuggets", 150.0, RESTAURANT_1);
    public static final Dish DISH_3 = new Dish(DISH_ID + 3, LocalDate.of(2020, 11, 17), "Chicken wings", 270.0, RESTAURANT_1);
    //RESTAURANT_2
    public static final Dish DISH_4 = new Dish(DISH_ID + 4, LocalDate.of(2020, 11, 18), "Cheeseburger", 110.0, RESTAURANT_2);
    public static final Dish DISH_5 = new Dish(DISH_ID + 5, LocalDate.now(), "Double cheeseburger", 150.0, RESTAURANT_2);
    public static final List<Dish> DISHES = Arrays.asList(DISH_0, DISH_1, DISH_2, DISH_3, DISH_4, DISH_5);
    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "restaurant");

    public static Dish getUpdatedDish() {
        return new Dish(DISH_ID + 4, LocalDate.now(), DISH_5.getName(), 65.0, RESTAURANT_2);
    }

    public static Dish getCreatedDish() {
        return new Dish(null, LocalDate.now(), "Caesar Salad", 160.0, RESTAURANT_2);
    }

}

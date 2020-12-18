package topjava.graduation.util;

import topjava.graduation.model.Restaurant;
import topjava.graduation.to.RestaurantTo;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantUtil {
    private RestaurantUtil() {
    }

    public static Restaurant createNewFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(restaurantTo.getId(), restaurantTo.getName());
    }

    public static RestaurantTo asTo(Restaurant restaurant) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }

    public static List<RestaurantTo> createListToFromListEntity(List<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::asTo)
                .collect(Collectors.toList());
    }
}

package topjava.graduation.to;

import lombok.Data;
import lombok.NoArgsConstructor;
import topjava.graduation.model.AbstractBaseEntity;
import topjava.graduation.model.Restaurant;


@NoArgsConstructor
@Data
public class RestaurantTo extends AbstractBaseEntity {
    private String name;

    public RestaurantTo(Restaurant restaurant) {
        this.name = restaurant.getName();
    }
}
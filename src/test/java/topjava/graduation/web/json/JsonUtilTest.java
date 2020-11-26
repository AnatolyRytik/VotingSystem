package topjava.graduation.web.json;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import topjava.graduation.GraduationApplication;
import topjava.graduation.model.Restaurant;

import java.util.List;

import static topjava.graduation.testdata.RestaurantTestData.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GraduationApplication.class)
class JsonUtilTest {

    @Test
    void readWriteValue() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANT_2);
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT_2);
    }

    @Test
    void readWriteValues() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANTS);
        System.out.println(json);
        List<Restaurant> restaurants = JsonUtil.readValues(json, Restaurant.class);
        RESTAURANT_MATCHER.assertMatch(restaurants, RESTAURANTS);
    }

}
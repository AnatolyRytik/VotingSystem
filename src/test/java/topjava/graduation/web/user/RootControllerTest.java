package topjava.graduation.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import topjava.graduation.TestUtil;
import topjava.graduation.model.Restaurant;
import topjava.graduation.service.RestaurantService;
import topjava.graduation.service.UserServiceSecurity;
import topjava.graduation.web.AbstractControllerTest;
import topjava.graduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static topjava.graduation.TestUtil.*;
import static topjava.graduation.testdata.DishTestData.DISH_ID;
import static topjava.graduation.testdata.DishTestData.DISH_MATCHER;
import static topjava.graduation.testdata.RestaurantTestData.*;
import static topjava.graduation.testdata.UserTestData.*;


class RootControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RootController.REST_URL + "/";
    @Autowired
    protected UserServiceSecurity userServiceSecurity;
    @Autowired
    protected RestaurantService restaurantService;

    @Test
    void testGetAllAuthorized() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(USER_0))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.writeValue(RESTAURANTS)))
                .andDo(print());
    }

    @Test
    void findByName() throws Exception {
        mockMvc.perform(get(REST_URL + "name?name=Burger King")
                .with(userAuth(USER_1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(RESTAURANT_2));
    }

    @Test
    void getAllTodayRestaurantsWithDishes() throws Exception {
        mockMvc.perform(get(REST_URL + "list")
                .with(userAuth(USER_1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(TODAY_RESTAURANTS));
    }
}
package topjava.graduation.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import topjava.graduation.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static topjava.graduation.TestUtil.contentJson;
import static topjava.graduation.TestUtil.userAuth;
import static topjava.graduation.testdata.RestaurantTestData.RESTAURANT_2;
import static topjava.graduation.testdata.RestaurantTestData.TODAY_RESTAURANTS;
import static topjava.graduation.testdata.UserTestData.USER_1;


class RestaurantUserControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantUserController.REST_URL + "/";

    @Test
    void testGetAllAuthorized() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
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
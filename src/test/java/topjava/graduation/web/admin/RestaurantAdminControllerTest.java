package topjava.graduation.web.admin;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import topjava.graduation.TestUtil;
import topjava.graduation.model.Restaurant;
import topjava.graduation.web.AbstractControllerTest;
import topjava.graduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static topjava.graduation.TestUtil.readFromJson;
import static topjava.graduation.TestUtil.userAuth;
import static topjava.graduation.testdata.RestaurantTestData.*;
import static topjava.graduation.testdata.UserTestData.ADMIN_0;
import static topjava.graduation.testdata.UserTestData.USER_1;

class RestaurantAdminControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantAdminController.REST_URL + "/";


    @Test
    void testDeleteForbidden() throws Exception {
        mockMvc.perform(delete(REST_URL + RES_ID)
                .with(userAuth(USER_1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(ADMIN_0))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.writeValue(RESTAURANTS)))
                .andDo(print());
    }

    @Test
    void getAllWithDishes() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(ADMIN_0))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.writeValue(RESTAURANTS)))
                .andDo(print());
    }

    @Test
    void testGetById() throws Exception {
        mockMvc.perform(get(REST_URL + (RES_ID + 1))
                .with(userAuth(ADMIN_0)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(RESTAURANT_1)));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RES_ID)
                .with(userAuth(ADMIN_0))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void testCreate() throws Exception {
        Restaurant expected = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(ADMIN_0))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        RESTAURANT_MATCHER.assertMatch(returned, expected);
        RESTAURANT_MATCHER.assertMatch(restaurantService.getById(returned.getId()), expected);
    }


    @Test
    void testUpdate() throws Exception {
        Restaurant expected = getUpdated();
        ResultActions action = mockMvc.perform(put(REST_URL + RES_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(TestUtil.userHttpBasic(ADMIN_0))
                .content(JsonUtil.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isOk());

        Restaurant returned = readFromJson(action, Restaurant.class);

        RESTAURANT_MATCHER.assertMatch(returned, expected);
        RESTAURANT_MATCHER.assertMatch(restaurantService.getById(RES_ID), expected);
    }

}

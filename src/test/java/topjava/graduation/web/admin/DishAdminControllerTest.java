package topjava.graduation.web.admin;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import topjava.graduation.TestUtil;
import topjava.graduation.to.DishTo;
import topjava.graduation.web.AbstractControllerTest;
import topjava.graduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static topjava.graduation.TestUtil.readFromJson;
import static topjava.graduation.TestUtil.userAuth;
import static topjava.graduation.testdata.DishTestData.*;
import static topjava.graduation.testdata.UserTestData.ADMIN_0;
import static topjava.graduation.testdata.UserTestData.USER_1;


class DishAdminControllerTest extends AbstractControllerTest {
    private static final String REST_URL = DishAdminController.REST_URL + "/";

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + (DISH_ID))
                .with(userAuth(USER_1)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(ADMIN_0))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonUtil.writeValue(DISHES)))
                .andDo(print());
    }

    @Test
    public void testGetById() throws Exception {
        mockMvc.perform(get(REST_URL + (DISH_ID + 5))
                .with(userAuth(ADMIN_0)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(DISH_5)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH_ID)
                .with(userAuth(ADMIN_0))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        DishTo created = getCreatedDish();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(ADMIN_0))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());

        DishTo returned = readFromJson(action, DishTo.class);
        created.setId(returned.getId());

        DISH_MATCHER.assertMatch(returned, created);
    }

    @Test
    public void testUpdate() throws Exception {
        DishTo expected = getUpdatedDish();
        ResultActions action = mockMvc.perform(put(REST_URL + (DISH_ID + 4))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .with(TestUtil.userHttpBasic(ADMIN_0))
                .content(JsonUtil.writeValue(expected)))
                .andDo(print())
                .andExpect(status().isOk());

        DishTo returned = TestUtil.readFromJson(action, DishTo.class);
        DISH_MATCHER.assertMatch(returned, expected);
    }
}
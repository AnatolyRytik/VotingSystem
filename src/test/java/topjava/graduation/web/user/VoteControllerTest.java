package topjava.graduation.web.user;

import org.junit.jupiter.api.Test;
import topjava.graduation.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static topjava.graduation.TestUtil.userHttpBasic;
import static topjava.graduation.testdata.RestaurantTestData.RES_ID;
import static topjava.graduation.testdata.UserTestData.USER_0;
import static topjava.graduation.testdata.UserTestData.USER_1;

class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserVoteController.REST_URL + "/";

    @Test
    void testGetUnAuthorized() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("date", "2020-11-24"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetUserVoteForDate() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("date", "2020-11-24")
                .with(userHttpBasic(USER_0)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void vote() throws Exception {
        mockMvc.perform(post(REST_URL)
                .param("restaurant_id", "2")
                .with(userHttpBasic(USER_1)))
                .andExpect(status().isOk());
    }
}

package topjava.graduation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import topjava.graduation.web.admin.DishAdminController;
import topjava.graduation.web.admin.RestaurantAdminController;
import topjava.graduation.web.user.RootController;
import topjava.graduation.web.user.UserVoteController;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GraduationApplication.class)
public class GraduationApplicationTests {

    @Autowired
    private RestaurantAdminController restaurantAdminController;

    @Autowired
    private DishAdminController dishAdminController;

    @Autowired
    private UserVoteController voteController;

    @Autowired
    private RootController rootController;

    @Test
    public void contextLoadsRestaurants() throws Exception {
        assertThat(restaurantAdminController).isNotNull();
    }

    @Test
    public void contextLoadsDishes() throws Exception {
        assertThat(dishAdminController).isNotNull();
    }

    @Test
    public void contextLoadsVotes() throws Exception {
        assertThat(voteController).isNotNull();
    }

    @Test
    public void contextLoadsRoot() throws Exception {
        assertThat(rootController).isNotNull();
    }

}
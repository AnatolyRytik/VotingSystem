package topjava.graduation.testdata;

import topjava.graduation.model.Role;
import topjava.graduation.model.User;

import java.util.Collections;


public class UserTestData {
    public static final Long START_SEQ = 0L;
    public static final Long USER_ID = START_SEQ+1;
    public static final Long ADMIN_ID = START_SEQ;


    public static final User USER_0 = new User(USER_ID, "User", "user@yandex.ru", "user", Role.ROLE_USER);
    public static final User USER_1 = new User(USER_ID+1, "UserOne", "userOne@yandex.ru", "userOne", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);

    public static User getNew() {
        return new User(null, "New user", "user@local.loc", "password", Role.ROLE_ADMIN);
    }

    public static User getUpdated() {
        User updated = new User(USER_0);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        return updated;
    }
}

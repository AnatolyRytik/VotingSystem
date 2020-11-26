package topjava.graduation.testdata;

import topjava.graduation.model.Role;
import topjava.graduation.model.User;


public class UserTestData {

    public static final Long START_SEQ = 0L;
    public static final Long USER_ID = START_SEQ + 1;
    public static final Long ADMIN_ID = START_SEQ;

    public static final User USER_0 = new User(USER_ID, "UserOne", "userone@yandex.ru", "useronepass", Role.USER);
    public static final User USER_1 = new User(USER_ID + 1, "UserTwo", "usertwo@yandex.ru", "usertwoopass", Role.USER);
    public static final User ADMIN_0 = new User(ADMIN_ID, "Admin", "admin@gmail.com", "adminpass", Role.ADMIN, Role.USER);
}

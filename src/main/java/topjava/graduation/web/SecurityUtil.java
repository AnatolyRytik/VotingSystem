package topjava.graduation.web;

import topjava.graduation.model.AbstractBaseEntity;

public class SecurityUtil {

    private static int id = 100001;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}
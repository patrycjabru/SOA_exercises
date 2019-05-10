import ejb.dto.User;

public class UserData {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserData.user = user;
    }
}

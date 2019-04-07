package pl.agh.kis.soa;

public class UserLoginData {
    private static String userName;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UserLoginData.userName = userName;
    }
}

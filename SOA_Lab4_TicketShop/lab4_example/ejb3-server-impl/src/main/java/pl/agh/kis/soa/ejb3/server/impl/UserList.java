package pl.agh.kis.soa.ejb3.server.impl;

import java.util.LinkedList;
import java.util.List;

public class UserList {
    private static List<User> users = new LinkedList<User>() {
        {
            add(new User("test","test",10));
        }
    };


    public static User checkLogin(String name, String password){
        for (User u :users) {
            if (u.getName().equals(name) && u.getPassword().equals(password))
                return u;
        }
        return null;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        UserList.users = users;
    }
}

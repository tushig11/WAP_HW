package Model;

import java.util.*;

public class UserData {
    private HashMap<String, User> users = new HashMap<>();

    public void addUser(User user){
        this.users.put(user.getUsername(), user);
    }
    public HashMap<String, User> getUsers(){
        generateUser();
        return this.users;
    }

    public void generateUser(){
        addUser(new User("admin","123"));
        addUser(new User("super", "123"));
        addUser(new User("manager", "123"));
        addUser(new User("member", "123"));
    }
}

package User;

import java.util.ArrayList;
import Main.User;

public class UserManager {

    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User u) {
        users.add(u);
    }

    // Authentication merged here
    public boolean login(String username, String password) {

        for (User u : users) {
            if (u.getUsername().equals(username)
                    && u.getPassword().equals(password))
                return true;
        }

        return false;
    }

    public int countUsers() {
        return users.size();
    }
}
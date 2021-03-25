package server.model;

import java.util.ArrayList;

public class UsersList {
    private ArrayList<String> usersList;
    private String type;
    
    public UsersList(ArrayList<String> usersList) {
        this.type = "usersList";
        this.usersList = usersList;
    }
    
    public ArrayList<String> getUsersList() {
        return usersList;
    }
    
    public String getType() {
        return type;
    }
}

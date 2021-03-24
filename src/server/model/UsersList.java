package server.model;

import java.util.ArrayList;

public class UsersList
{
    private ArrayList<String> usersList;
    private String type;

    public UsersList(String type, ArrayList<String> usersList)
    {
        this.type = type;
        this.usersList = usersList;
    }
    public ArrayList<String> getUsersList(){
        return usersList;
    }
    public String getType(){
        return type;
    }
}

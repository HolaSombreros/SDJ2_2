package server.model;

import java.util.ArrayList;

public class UsersList
{
    private ArrayList<String> usersList;

    public UsersList()
    {
        this.usersList = new ArrayList<>();
    }
    public ArrayList<String> getUsersList(){
        return usersList;
    }
}

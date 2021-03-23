package client.model;

import util.NamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends NamedPropertyChangeSubject
{
    void login(String username);
    ArrayList<String> getOnlineUsersList();
    void sendPublicMessage(String message);
    String getUsername();
    void disconnect();
}

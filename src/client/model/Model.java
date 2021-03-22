package client.model;

import util.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject
{
    void login(String username);
    void getOnlineUsersList();
    void sendPublicMessage(String message);
    void getUsername();
    void disconnect();
}

package client.model;

public interface Model {
    void login(String username);
    void getOnlineUsersList();
    void sendPublicMessage(String message);
    void getUsername();
    void disconnect();

}

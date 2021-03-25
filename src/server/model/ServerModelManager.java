package server.model;

import server.ServerLog;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ServerModelManager implements ServerModel {
    private PropertyChangeSupport property;
    private ArrayList<String> clients;
    
    public ServerModelManager() {
        property = new PropertyChangeSupport(this);
        clients = new ArrayList<>();
    }
    
    @Override
    public ArrayList<String> getOnlineUsers() {
        return clients;
    }
    
    /**
     * @param username Adds the user to the existing user list if it's new or sends an error message if the username already exists
     *                 If the user is logged in, the message is added to the log
     */
    @Override
    public void login(String username) {
        Message message;
        if (clients.contains(username)) {
            message = new Message("error", null, null);
            property.firePropertyChange("error", null, message);
        }
        else {
            clients.add(username);
            message = new Message("login", username, "connected to the server!");
            property.firePropertyChange("login", null, message);
            ServerLog.getInstance().addLog(message);
        }
    }
    
    /**
     * @param message fires a message to the server
     *                adds the message to the log
     */
    @Override
    public void sendMessage(Message message) {
        property.firePropertyChange("message", null, message);
        ServerLog.getInstance().addLog(message);
    }
    
    /**
     * @param username removes the user from the current user list
     *                 fires a message to the server and adds the message to the log
     */
    @Override
    public void disconnect(String username) {
        clients.remove(username);
        Message message = new Message("disconnect", username, "disconnected from the server");
        property.firePropertyChange("disconnect", null, message);
        ServerLog.getInstance().addLog(message);
    }
    
    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        if (propertyName == null) {
            property.addPropertyChangeListener(listener);
        }
        else {
            property.addPropertyChangeListener(propertyName, listener);
        }
    }
    
    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        if (propertyName == null) {
            property.removePropertyChangeListener(listener);
        }
        else {
            property.removePropertyChangeListener(propertyName, listener);
        }
    }
}

package client.model;

import client.mediator.ChatClient;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

public class ModelManager implements Model, PropertyChangeListener {
    private ChatClient chatClient;
    private PropertyChangeSupport property;
    
    public ModelManager() {
        chatClient = null;
        property = new PropertyChangeSupport(this);
    }
    
    @Override
    public void login(String username) {
        try {
            chatClient = new ChatClient(this, "localhost", 1234, username);
            chatClient.addListener(null, this);
            chatClient.login(username);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void getOnlineUsersList() {
        chatClient.getOnlineUsersList();
    }
    
    @Override
    public void sendPublicMessage(String message) {
        chatClient.sendPublicMessage(message);
    }
    
    @Override
    public void getUsername() {
        chatClient.getUsername();
    }
    
    @Override
    public void disconnect() {
        chatClient.disconnect();
        chatClient = null;
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
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        property.firePropertyChange(evt);
    }
}

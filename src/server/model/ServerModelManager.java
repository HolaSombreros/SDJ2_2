package server.model;

import server.mediator.ClientHandler;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ServerModelManager implements ServerModel
{
  private PropertyChangeSupport property;
  private ArrayList<String> clients;

  public ServerModelManager()
  {
    property = new PropertyChangeSupport(this);
    clients = new ArrayList<>();
  }

  @Override public String getOnlineUsers()
  {
    return null;
  }

  @Override public String getChatLog()
  {
    return null;
  }
  
  @Override
  public void login(String username) {
    clients.add(username);
    property.firePropertyChange("login", null, username + " connected to the server!");
  }
  
  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    if (propertyName == null) {
      property.addPropertyChangeListener(listener);
    }
    else {
      property.addPropertyChangeListener(propertyName, listener);
    }
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    if (propertyName == null) {
      property.removePropertyChangeListener(listener);
    }
    else {
      property.removePropertyChangeListener(propertyName, listener);
    }
  }
}

package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model
{
  private PropertyChangeSupport property;

  public ModelManager()
  {
    property = new PropertyChangeSupport(this);
  }

  @Override public String getOnlineUsers()
  {
    return null;
  }

  @Override public String getChatLog()
  {
    return null;
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName, listener);
  }

}

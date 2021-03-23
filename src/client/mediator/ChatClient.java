package client.mediator;

import client.model.Model;
import com.google.gson.Gson;
import server.model.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ChatClient implements Model
{
  private Model model;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;
  private Message receivedMessage;
  private String user;
  private boolean waiting;
  private PropertyChangeSupport property;

  public ChatClient(Model model, String host, int port, String user) throws IOException
  {
    this.model = model;
    socket = new Socket(host, port);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    gson = new Gson();
    waiting = false;
    receivedMessage = null;
    //this.user = user;
    property = new PropertyChangeSupport(this);
    ClientReceiver clientReceiver = new ClientReceiver(this, in);
    Thread t = new Thread(clientReceiver);
    t.start();
  }

  public synchronized void received(String received)
  {
    Message receivedMessage = gson.fromJson(received,Message.class);
    switch(receivedMessage.getType()){
      case "login":
        if(receivedMessage.getText().equals("The username already exists"))
            throw new IllegalArgumentException(receivedMessage.getText());
        user = receivedMessage.getUsername();
        property.firePropertyChange("login", null, receivedMessage);
        System.out.println("Sent message to modelmanager");
        break;
      case "message":
        property.firePropertyChange("message", null, receivedMessage);
        break;
    }

  }

  private synchronized void waitingForReply()
  {
    waiting = true;
    while (waiting)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public void login(String username)
  {
    Message message = new Message("login",username,null);
    String messageJson = gson.toJson(message);
    out.println(messageJson);
    waitingForReply();
  }

  @Override public ArrayList<String> getOnlineUsersList()
  {
    // TODO - implement
    return null;
  }

  @Override public void sendPublicMessage(String message)
  {
    Message publicMessage = new Message("message",user,message);
    String messageJson = gson.toJson(publicMessage);
    out.println(messageJson);
  }

  @Override public String getUsername()
  {
    // TODO - implement
    return null;
  }

  @Override public void disconnect()
  {
    try
    {
      in.close();
      out.close();
      socket.close();
      waiting = false;
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void addListener(String propertyName, PropertyChangeListener listener)
  {
    if (propertyName == null)
    {
      property.addPropertyChangeListener(listener);
    }
    else
    {
      property.addPropertyChangeListener(propertyName, listener);
    }
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    if (propertyName == null)
    {
      property.removePropertyChangeListener(listener);
    }
    else
    {
      property.removePropertyChangeListener(propertyName, listener);
    }
  }
}

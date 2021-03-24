package client.mediator;

import client.model.Model;
import com.google.gson.Gson;
import server.model.Message;
import server.model.UsersList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

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
  private ArrayList<String> usersList;

  public ChatClient(Model model, String host, int port) throws IOException
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
    usersList = new ArrayList<>();
    ClientReceiver clientReceiver = new ClientReceiver(this, in);
    Thread t = new Thread(clientReceiver);
    t.start();
  }

  public synchronized void received(String received)
  {

    if(!gson.fromJson(received, Map.class).get("type").equals("usersList")){
      Message receivedMessage = gson.fromJson(received,Message.class);
      switch(receivedMessage.getType()) {
        case "login":
          user = receivedMessage.getUsername();
          property.firePropertyChange("login", null, receivedMessage);
          break;
        case "message":
          property.firePropertyChange("message", null, receivedMessage);
          break;
        }
    }
    else{

        usersList = gson.fromJson(received, UsersList.class).getUsersList();
      }
//      case "error":
        notify();
    }



  private synchronized void waitingForReply()
  {
    waiting = true;
    while (waiting)
    {
      try
      {
        wait();
        waiting = false;
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
    //waitingForReply();
  }

  @Override public ArrayList<String> getOnlineUsersList()
  {
    //send a message to server to request an arraylist with all users
    //server sends back an arraylist with all users
    Message message = new Message("usersList",user,null);
    String messageJson = gson.toJson(message);
    out.println(messageJson);
    waitingForReply();


    return usersList;
  }

  @Override public void sendPublicMessage(String message)
  {
    Message publicMessage = new Message("message",user,message);
    String messageJson = gson.toJson(publicMessage);
    out.println(messageJson);
  }

  @Override public String getUsername()
  {
    return user;
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

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
    this.user = user;
    property = new PropertyChangeSupport(this);
    ClientReceiver clientReceiver = new ClientReceiver(this, in);
    Thread t = new Thread(clientReceiver);
    t.start();
  }

  public synchronized void received(Message received)
  {

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

  }

  @Override public void getOnlineUsersList()
  {

  }

  @Override public void sendPublicMessage(String message)
  {

  }

  @Override public void getUsername()
  {

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
    property.addPropertyChangeListener(propertyName,listener);
  }

  @Override public void removeListener(String propertyName, PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(propertyName, listener);
  }
}

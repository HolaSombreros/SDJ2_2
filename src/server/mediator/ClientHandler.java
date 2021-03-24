package server.mediator;

import com.google.gson.Gson;
import server.model.Message;
import server.model.ServerModel;
import server.model.UsersList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable, PropertyChangeListener
{
  private ServerModel serverModel;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Gson gson;

  public ClientHandler(Socket socket, ServerModel serverModel) throws IOException
  {
    this.serverModel = serverModel;
    serverModel.addListener(null, this);
    this.socket = socket;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    running = true;
    gson = new Gson();
  }

  @Override public void run()
  {
    while (running)
    {
      try
      {
        String request = in.readLine();
        Message message = gson.fromJson(request, Message.class);

        switch (message.getType())
        {
          case "login":
            serverModel.login(message.getUsername());
            break;
          case "message":
            serverModel.sendMessage(message);
            break;
          case "usersList":
            UsersList usersList = new UsersList(serverModel.getOnlineUsers());
            String userListJson = gson.toJson(usersList);
            System.out.println("Sending user list to client");
            out.println(userListJson);
            break;
          case "disconnect":
            serverModel.disconnect(message.getUsername());
        }
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Message message;
    String reply;
    switch (evt.getPropertyName())
    {
      case "login":
      case "error":
      case "message":
      case "disconnect":
        message = (Message) evt.getNewValue();
        reply = gson.toJson(message);
        out.println(reply);
        break;
    }
  }
}

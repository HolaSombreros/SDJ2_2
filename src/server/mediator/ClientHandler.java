package server.mediator;

import com.google.gson.Gson;
import server.model.ServerModel;

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
    out = new PrintWriter(socket.getOutputStream(),true);
    running = true;
    gson = new Gson();
  }

  @Override public void run()
  {
    while (running)
    {
      try
      {
        in.readLine();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName()) {
      case "login":
        out.println(socket.getInetAddress().getHostAddress() + " connected to the server!");
        System.out.println(socket.getInetAddress().getHostAddress() + " connected to the server!");
        break;
    }
  }
}

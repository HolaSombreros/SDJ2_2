package server.mediator;

import com.google.gson.Gson;
import server.model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable, PropertyChangeListener
{
  private Model model;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Gson gson;

  public ClientHandler(Socket socket, Model model) throws IOException
  {
    this.model = model;
    // TODO MODEL.ADDLISTENER()
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

  }
}
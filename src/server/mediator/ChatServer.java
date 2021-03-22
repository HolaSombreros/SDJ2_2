package server.mediator;

import server.model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable
{
  private int PORT = 1234;
  private boolean running;
  private ServerSocket welcomeSocket;
  private Model model;

  public ChatServer(Model model) throws IOException
  {
    this.model = model;
    running = true;
    welcomeSocket = new ServerSocket(PORT);
  }

  public void close()
  {
    running = false;
  }

  @Override public void run()
  {
    while (running)
    {
      try
      {
        Socket socket = welcomeSocket.accept();
        ClientHandler clientHandler = new ClientHandler(socket,model);
        Thread t = new Thread(clientHandler);
        t.start();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

  }
}

package server.mediator;

import server.model.ServerModel;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer implements Runnable
{
  public static final  int PORT = 1234;
  private boolean running;
  private ServerSocket welcomeSocket;
  private ServerModel serverModel;

  public ChatServer(ServerModel serverModel) throws IOException
  {
    this.serverModel = serverModel;
    running = true;
    welcomeSocket = new ServerSocket(PORT);
  }

  public void close()
  {
    running = false;
  }

  /**
   * while the server is running creates in a try-catch block a new client socket and a new ClientHandler thread
   * starts the thread
   * */
  @Override public void run()
  {
    while (running)
    {
      try
      {
        Socket socket = welcomeSocket.accept();
        ClientHandler clientHandler = new ClientHandler(socket, serverModel);
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

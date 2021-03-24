package client.mediator;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReceiver implements Runnable
{
  private ChatClient client;
  private BufferedReader in;
  private boolean connected;

  public ClientReceiver(ChatClient client, BufferedReader in)
  {
    this.client = client;
    this.in = in;
    connected = true;
  }

  public void disconnect()
  {
    connected = false;
  }

  @Override public void run()
  {
    while (connected)
    {
      try
      {
        client.received(in.readLine());
      }
      catch (IOException e)
      {
        disconnect();
      }
    }
    try
    {
      in.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}

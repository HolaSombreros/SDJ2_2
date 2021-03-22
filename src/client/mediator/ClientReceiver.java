package client.mediator;

import com.google.gson.Gson;
import server.model.Message;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientReceiver implements Runnable {
    private ChatClient client;
    private BufferedReader in;
    private boolean connected;
    private Gson gson;
    
    public ClientReceiver(ChatClient client, BufferedReader in) {
        this.client = client;
        this.in = in;
        connected = true;
        gson = new Gson();
    }
    
    public void disconnect() {
        connected = false;
    }
    
    @Override
    public void run() {
        while (connected) {
            try {
                Message message = gson.fromJson(in.readLine(), Message.class);
                client.received(message);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

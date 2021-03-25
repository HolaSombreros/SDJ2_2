package server;

import server.mediator.ChatServer;
import server.model.ServerModel;
import server.model.ServerModelManager;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerModel serverModel = new ServerModelManager();
        Thread server = new Thread(new ChatServer(serverModel));
        server.start();
    }
}

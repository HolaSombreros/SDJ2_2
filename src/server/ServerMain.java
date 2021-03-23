package server;

import server.mediator.ChatServer;
import server.model.ServerModel;
import server.model.ServerModelManager;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerModel serverModel = new ServerModelManager();
//        ServerLog serverLog = ServerLog.getInstance();
        Thread server = new Thread(new ChatServer(serverModel));
        server.start();
//        serverLog.addLog(new Message("Test message", "192.168.0.1"));
    }
}

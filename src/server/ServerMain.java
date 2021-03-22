package server;

import server.mediator.ChatServer;
import server.model.Message;
import server.model.Model;
import server.model.ModelManager;

import java.io.IOException;
import java.time.LocalDate;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Model model = new ModelManager();
//        ServerLog serverLog = ServerLog.getInstance(LocalDate.now().toString());
        Thread server = new Thread(new ChatServer(model));
        server.start();
//        serverLog.addLog(new Message("Test message", "192.168.0.1"));
    }
}
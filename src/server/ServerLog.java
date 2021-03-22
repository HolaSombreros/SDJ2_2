package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import server.model.Message;

public class ServerLog {
    private static Map<String, ServerLog> instances = new HashMap<>();
    private ArrayList<Message> log;
    private String fileName;
    
    private ServerLog(String fileName) {
        this.log = new ArrayList<>();
        this.fileName = fileName;
    }
    
    public static ServerLog getInstance(String fileName) {
        ServerLog instance = instances.get(fileName);
        if (instance == null) {
            synchronized (instances) {
                instance = instances.get(fileName);
                if (instance == null) {
                    instance = new ServerLog(fileName);
                    instances.put(fileName, instance);
                }
            }
        }
        return instance;
    }
    
    public void addLog(Message message) {
        log.add(message);
        log(message);
    }
    
    private void log(Message message) {
        if (log == null) {
            return;
        }
        BufferedWriter out = null;
         try {
             out = new BufferedWriter(new FileWriter("Log-" + fileName + ".txt", true));
             out.write(message.toString() + "\n");
         }
         catch (Exception e) {
             e.printStackTrace();
         }
         finally {
             try {
                 out.close();
             }
             catch (Exception e) {
                 e.printStackTrace();
             }
         }
    }
}

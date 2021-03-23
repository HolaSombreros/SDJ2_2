package server;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import server.model.Message;

public class ServerLog {
    private static Map<LocalDate, ServerLog> instances = new HashMap<>();
    private ArrayList<Message> log;
    
    private ServerLog() {
        this.log = new ArrayList<>();
    }
    
    public static ServerLog getInstance() {
        LocalDate today = LocalDate.now();
        ServerLog instance = instances.get(today);
        if (instance == null) {
            synchronized (instances) {
                instance = instances.get(today);
                if (instance == null) {
                    instance = new ServerLog();
                    instances.put(today, instance);
                }
            }
        }
        return instance;
    }
    
    public synchronized void addLog(Message message) {
        log.add(message);
        log(message);
    }
    
    private void log(Message message) {
        if (log == null) {
            return;
        }
        BufferedWriter out = null;
         try {
             out = new BufferedWriter(new FileWriter("Log-" + LocalDate.now().toString() + ".txt", true));
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

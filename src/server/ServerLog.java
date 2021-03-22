package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerLog {
    private static Map<String, ServerLog> instances = new HashMap<>();
    private ArrayList<String> _log;
    private String _fileName;
    
    private ServerLog(String fileName) {
        _log = new ArrayList<>();
        _fileName = fileName;
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
    
    public void addLog(String text) {
    
    }
}

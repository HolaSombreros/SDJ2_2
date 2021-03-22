package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerLog {
    private static Map<String, ServerLog> _instances = new HashMap<>();
    private ArrayList<String> _log;
    private String _fileName;
    
    private ServerLog(String fileName) {
        _log = new ArrayList<>();
        _fileName = fileName;
    }
    
    public static ServerLog getInstance(String fileName) {
        ServerLog instance = _instances.get(fileName);
        if (instance == null) {
            synchronized (_instances) {
                instance = _instances.get(fileName);
                if (instance == null) {
                    instance = new ServerLog(fileName);
                    _instances.put(fileName, instance);
                }
            }
        }
        return instance;
    }
    
    public void addLog(String text) {
    
    }
}

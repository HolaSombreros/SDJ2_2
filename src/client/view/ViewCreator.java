package client.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class ViewCreator {
    private Map<View, ViewController> _viewControllerMap;
    
    public ViewCreator() {
        _viewControllerMap = new HashMap<>();
    }
    
    public ViewController getViewController(View view) {
        ViewController controller = _viewControllerMap.get(view);
        if (controller == null) {
            controller = loadFromFXML(view.getFxmlFile());
            _viewControllerMap.put(view, controller);
        }
        else {
            _viewControllerMap.get(view).reset();
        }
        return controller;
    }
    
    protected abstract void initViewController(ViewController controller, Region root);
    
    private ViewController loadFromFXML(String fxmlFile) {
        ViewController viewController = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = getClass().getResource(fxmlFile);
            loader.setLocation(url);
            Region root = loader.load();
            viewController = loader.getController();
            initViewController(viewController, root);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return viewController;
    }
}

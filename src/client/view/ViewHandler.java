package client.view;

import client.viewmodel.ViewModelFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler extends ViewCreator {
    private Stage _stage;
    private Scene _scene;
    private ViewModelFactory _viewModelFactory;
    
    public ViewHandler(ViewModelFactory viewModelFactory) {
        super();
        _viewModelFactory = viewModelFactory;
    }
    
    public void start(Stage stage) {
        _stage = stage;
        _scene = new Scene(new Region());
        openView(View.MAINVIEW);
    }
    
    public void openView(View view) {
        ViewController viewController = getViewController(view);
        Region root = viewController.getRoot();
        _scene.setRoot(root);
        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        _stage.setTitle(title);
        _stage.setScene(_scene);
        _stage.setWidth(root.getPrefWidth());
        _stage.setHeight(root.getPrefHeight());
        _stage.setResizable(false);
        _stage.show();
    }
    
    @Override
    protected void initViewController(ViewController controller, Region root) {
        controller.init(this, _viewModelFactory, root);
    }
}

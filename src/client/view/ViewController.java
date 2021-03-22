package client.view;

import client.viewmodel.ViewModelFactory;
import javafx.scene.layout.Region;

public abstract class ViewController {
    private Region _root;
    private ViewHandler _viewHandler;
    private ViewModelFactory _viewModelFactory;
    
    protected abstract void init();
    
    public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory, Region root) {
        _viewHandler = viewHandler;
        _viewModelFactory = viewModelFactory;
        _root = root;
        init();
    }
    
    public void reset() {
        // TODO: Implement method.
    }
    
    public Region getRoot() {
        return _root;
    }
    
    public ViewModelFactory getViewModelFactory() {
        return _viewModelFactory;
    }
    
    public ViewHandler getViewHandler() {
        return _viewHandler;
    }
}

package client.viewmodel;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    private Model _model;
    private StringProperty _username;
    
    public LoginViewModel(Model model) {
        _model = model;
        _username = new SimpleStringProperty();
    }
    
    public StringProperty getUsernameProperty() {
        return _username;
    }
    
    public void reset() {
        _username.set("");
    }
}

package client.viewmodel;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
    private Model model;
    private StringProperty username;
    
    public LoginViewModel(Model model) {
        this.model = model;
        username = new SimpleStringProperty();
    }
    
    public StringProperty getUsernameProperty() {
        return username;
    }
    
    public void reset() {
        username.set("");
    }
}

package client.viewmodel;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel
{
    private Model model;
    private StringProperty username;
    private StringProperty error;
    
    public LoginViewModel(Model model) {
        this.model = model;
        username = new SimpleStringProperty();
        error = new SimpleStringProperty("");
    }
    
    public StringProperty getUsernameProperty() {
        return username;
    }
    
    public StringProperty getErrorProperty() {
        return error;
    }
    
    public void reset() {
        username.set("");
        error.set("");
    }
    
    public boolean login() {
        try {
            model.login(username.get());
            return true;
        }
        catch (Exception e) {
            error.set(e.getMessage());
            return false;
        }
    }
}

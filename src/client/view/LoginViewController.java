package client.view;

import client.viewmodel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class LoginViewController extends ViewController {
    private LoginViewModel viewModel;
    
    @FXML private TextField usernameInput;
    @FXML private Label errorLabel;
    
    public LoginViewController() {
    
    }

    @Override
    protected void init() {
        viewModel = getViewModelFactory().getLoginViewModel();
        usernameInput.textProperty().bindBidirectional(viewModel.getUsernameProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
    }
    
    @Override
    public void reset() {
        viewModel.reset();
    }
    
    @FXML
    private void login() {
        if (viewModel.login()) {
            getViewHandler().openView(View.CHATVIEW);
        }
    }

    public void onEnter(javafx.event.ActionEvent event)
    {
        if(event.getSource() == usernameInput){
            usernameInput.requestFocus();
            login();
        }
    }
}

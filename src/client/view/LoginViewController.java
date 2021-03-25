package client.view;

import client.viewmodel.LoginViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    
    @FXML
    private void onEnter(ActionEvent e) {
        login();
    }
}

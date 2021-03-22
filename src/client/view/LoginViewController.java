package client.view;

import client.viewmodel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginViewController extends ViewController {
    private LoginViewModel viewModel;
    
    @FXML
    private TextField usernameInput;
    
    public LoginViewController() {
    
    }
    
    @Override
    protected void init() {
        viewModel = getViewModelFactory().getLoginViewModel();
        usernameInput.textProperty().bindBidirectional(viewModel.getUsernameProperty());
    }
    
    @Override
    public void reset() {
        viewModel.reset();
    }
    
    @FXML
    private void connect() {
        getViewHandler().openView(View.CHATVIEW);
    }
}

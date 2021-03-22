package client.view;

import client.viewmodel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginViewController extends ViewController {
    private LoginViewModel _viewModel;
    
    @FXML
    private TextField usernameInput;
    
    public LoginViewController() {
    
    }
    
    @Override
    protected void init() {
        _viewModel = getViewModelFactory().getLoginViewModel();
        usernameInput.textProperty().bindBidirectional(_viewModel.getUsernameProperty());
    }
    
    @Override
    public void reset() {
        _viewModel.reset();
    }
    
    @FXML
    private void connect() {
        getViewHandler().openView(View.CHATVIEW);
    }
}

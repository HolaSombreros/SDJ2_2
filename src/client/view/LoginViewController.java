package client.view;

import client.viewmodel.LoginViewModel;

public class LoginViewController extends ViewController {
    private LoginViewModel _viewModel;
    
    public LoginViewController() {
    
    }
    
    @Override
    protected void init() {
        _viewModel = getViewModelFactory().getLoginViewModel();
    }
}

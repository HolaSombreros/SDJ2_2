package client.viewmodel;

import client.model.Model;

public class ViewModelFactory {
    private LoginViewModel _loginViewModel;
    
    public ViewModelFactory(Model model) {
        _loginViewModel = new LoginViewModel(model);
    }
    
    public LoginViewModel getLoginViewModel() {
        return _loginViewModel;
    }
}

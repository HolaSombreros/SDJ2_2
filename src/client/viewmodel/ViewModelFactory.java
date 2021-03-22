package client.viewmodel;

import client.model.Model;

public class ViewModelFactory {
    private LoginViewModel _loginViewModel;
    private ChatViewModel _chatViewModel;
    
    public ViewModelFactory(Model model) {
        _loginViewModel = new LoginViewModel(model);
        _chatViewModel = new ChatViewModel(model);
    }
    
    public LoginViewModel getLoginViewModel() {
        return _loginViewModel;
    }
    
    public ChatViewModel getChatViewModel() {
        return _chatViewModel;
    }
}

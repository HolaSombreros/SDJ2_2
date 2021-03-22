package client.viewmodel;

import client.model.Model;

public class ViewModelFactory {
    private MainViewModel _mainViewModel;
    private PasswordGeneratorViewModel _passwordGeneratorViewModel;
    
    public ViewModelFactory(Model model) {
        _mainViewModel = new MainViewModel(model);
        _passwordGeneratorViewModel = new PasswordGeneratorViewModel(model);
    }
    
    public MainViewModel getMainViewModel() {
        return _mainViewModel;
    }
    
    public PasswordGeneratorViewModel getPasswordGeneratorViewModel() {
        return _passwordGeneratorViewModel;
    }
    
    public PasswordTableViewModel getPasswordListViewModel() {
        return _passwordTableViewModel;
    }
}

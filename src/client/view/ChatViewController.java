package client.view;

import client.viewmodel.ChatViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ChatViewController extends ViewController {

    @FXML
    private TextField clientMessageInput;
    private ChatViewModel chatViewModel;



    @FXML private void sendButton(ActionEvent event){

    }

    @FXML private void disconnect(ActionEvent event){

    }
    @Override
    protected void init() {
  
    }
    
    @Override
    public void reset() {
    
    }

}

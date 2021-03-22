package client.view;

import client.viewmodel.ChatViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



public class ChatViewController extends ViewController {

    @FXML
    private TextField clientMessageInput;
    @FXML private ListView<String> chatRoom;
    @FXML private ListView<String> usersRoom;
    private ChatViewModel chatViewModel;



    @FXML private void sendButton(ActionEvent event){

    }

    @FXML private void disconnect(ActionEvent event){

    }
    @Override
    protected void init() {
        chatViewModel = getViewModelFactory().getChatViewModel();
        clientMessageInput.textProperty().bindBidirectional(chatViewModel.getTextFieldInput());

    }
    
    @Override
    public void reset() {
        chatViewModel.reset();
    }

}

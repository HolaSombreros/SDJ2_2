package client.view;

import client.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



public class ChatViewController extends ViewController {

    @FXML
    private TextField clientMessageInput;
    @FXML private ListView<String> chatRoom;
    @FXML private ListView<String> usersRoom;
    private ChatViewModel chatViewModel;

    public ChatViewController(){

    }

    @FXML private void sendButton(){
        chatViewModel.sendMessage();
    }

    @FXML private void disconnect(){
        chatViewModel.disconnect();
    }
    @Override
    protected void init() {
        chatViewModel = getViewModelFactory().getChatViewModel();
        clientMessageInput.textProperty().bindBidirectional(chatViewModel.getTextFieldInput());
        chatRoom.setItems(chatViewModel.getChatList());
        usersRoom.setItems(chatViewModel.getUsersList());

    }
    
    @Override
    public void reset() {
        chatViewModel.reset();
    }

}

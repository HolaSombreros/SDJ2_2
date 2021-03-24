package client.view;

import client.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class ChatViewController extends ViewController {

    @FXML private TextField clientMessageInput;
    @FXML private ListView<String> chatRoom;
    @FXML private ListView<String> usersRoom;
    private ChatViewModel chatViewModel;

    public ChatViewController(){

    }
    @FXML public void onEnter(javafx.event.ActionEvent event)
    {
        if(event.getSource() == clientMessageInput){
            clientMessageInput.requestFocus();
           sendButton();
        }
    }

    @FXML private void sendButton(){
        chatViewModel.sendMessage();
        sound();
    }

    @FXML private void disconnect(){
        chatViewModel.disconnect();
        getViewHandler().openView(View.LOGINVIEW);
    }
    
    @FXML private void users() {
        chatViewModel.getUsers();
    }
    
    @Override
    protected void init() {
        chatViewModel = getViewModelFactory().getChatViewModel();
        clientMessageInput.textProperty().bindBidirectional(chatViewModel.getTextFieldInput());
        chatRoom.setItems(chatViewModel.getChatList());
        usersRoom.setItems(chatViewModel.getUsersList());
    }
    
    public void sound(){
        String path = "src/util/notification_sound_1.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    
    @Override
    public void reset() {
        chatViewModel.reset();
    }
}

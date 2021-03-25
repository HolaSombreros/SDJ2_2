package client.view;

import client.viewmodel.ChatViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ChatViewController extends ViewController {
    private ChatViewModel chatViewModel;
    
    @FXML private TextField clientMessageInput;
    @FXML private Label userLabel;
    @FXML private ListView<String> usersRoom;
    @FXML private VBox chatRoom;
    @FXML private ScrollPane scrollPane;
    
    public ChatViewController() {
    
    }
    
    @Override
    protected void init() {
        chatViewModel = getViewModelFactory().getChatViewModel();
        clientMessageInput.textProperty().bindBidirectional(chatViewModel.getTextFieldInput());
        userLabel.textProperty().bind(chatViewModel.getUsernameProperty());
        usersRoom.setItems(chatViewModel.getUsersList());
        Bindings.bindContentBidirectional(chatViewModel.getMessageList(), chatRoom.getChildren());
        scrollPane.vvalueProperty().bind(chatRoom.heightProperty());
    }
    
    @Override
    public void reset() {
        chatViewModel.reset();
    }
    
    @FXML
    public void onEnter() {
        sendButton();
    }
    
    @FXML
    private void sendButton() {
        chatViewModel.sendMessage();
//        sound();
    }
    
    @FXML
    private void disconnect() {
        chatViewModel.disconnect();
        getViewHandler().openView(View.LOGINVIEW);
    }
    
    @FXML
    private void users() {
        chatViewModel.getUsers();
    }
    
    public void sound() {
        String path = "src/util/notification_sound_1.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}

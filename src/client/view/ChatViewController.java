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
    
    @FXML private TextField clientMessageInput;
    @FXML private VBox chatRoom;
    @FXML private ListView<String> usersRoom;
    @FXML private Label userLabel;
    @FXML private ScrollPane scrollPane;
    private ChatViewModel chatViewModel;

    @Override
    protected void init() {
        chatViewModel = getViewModelFactory().getChatViewModel();
        clientMessageInput.textProperty().bindBidirectional(chatViewModel.getTextFieldInput());
        Bindings.bindContentBidirectional(chatViewModel.getMessageList(), chatRoom.getChildren());
        chatRoom.setMaxHeight(Double.MAX_VALUE);
        scrollPane.prefHeightProperty().bind(chatRoom.heightProperty());
        usersRoom.setItems(chatViewModel.getUsersList());
        userLabel.textProperty().bind(chatViewModel.getUsernameProperty());
        scrollPane.vvalueProperty().bind(chatRoom.heightProperty());
       // scrollPane.setVvalue(1.0d);
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

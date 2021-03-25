package client.viewmodel;

import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import server.model.Message;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener {
    private Model model;
    private ObservableList<String> usersList;
    private StringProperty username;
    private StringProperty textFieldInput;
    private ObservableList<Node> messageList;
    
    public ChatViewModel(Model model) {
        this.model = model;
        this.usersList = FXCollections.observableArrayList();
        this.username = new SimpleStringProperty("Welcome, #");
        this.textFieldInput = new SimpleStringProperty();
        this.messageList = FXCollections.observableArrayList();
        model.addListener(null, this);
        reset();
    }
    
    public void reset() {
        username.set("Welcome, #");
        textFieldInput.set("");
        messageList.clear();
        usersList.clear();
    }
    public ObservableList<Node> getMessageList(){
        return messageList;
    }
    
    public ObservableList<String> getUsersList() {
        return usersList;
    }

    
    public StringProperty getUsernameProperty() {
        return username;
    }
    
    public StringProperty getTextFieldInput() {
        return textFieldInput;
    }
    
    public void disconnect() {
        model.disconnect();
    }
    
    public void sendMessage() {
        if (textFieldInput != null && !textFieldInput.get().trim().isEmpty()) {
            model.sendPublicMessage(textFieldInput.get());
            textFieldInput.set("");
        }
    }
    
    public void getUsers() {
        usersList.clear();
        usersList.addAll(model.getOnlineUsersList());
    }
    public HBox addMessageBox(Message message){
        HBox hbox = new HBox();
        Label messageLabel = new Label(message.getUsername() +  ": " + message.getText());
        hbox.getChildren().add(messageLabel);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setPrefWidth(0);
        hbox.setPrefHeight(VBox.USE_COMPUTED_SIZE);
        messageLabel.setMaxWidth(381);
        messageLabel.prefHeight(Label.USE_COMPUTED_SIZE);
        messageLabel.setWrapText(true);
        messageList.add(hbox);
        return hbox;
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            Message message;
            switch (evt.getPropertyName()) {
                case "login":
                case "disconnect":
                case "message":
                    message = (Message) evt.getNewValue();
                    addMessageBox(message);
                    break;
                case "username":
                    username.set(username.get().replace("#", (String) evt.getNewValue()));
                    break;
            }
        });
    }
}

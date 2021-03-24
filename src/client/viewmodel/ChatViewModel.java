package client.viewmodel;

import client.model.Model;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.Message;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChatViewModel implements PropertyChangeListener {
    private Model model;
    private ObservableList<String> usersList;
    private ObservableList<String> chatList;
    private StringProperty textFieldInput;
    
    public ChatViewModel(Model model) {
        this.model = model;
        this.chatList = FXCollections.observableArrayList();
        this.usersList = FXCollections.observableArrayList();
        this.textFieldInput = new SimpleStringProperty();
        model.addListener(null, this);
    }
    
    public void reset() {
        textFieldInput.set(null);
        chatList.clear();
        usersList.clear();
    }
    
    public ObservableList<String> getUsersList() {
        return usersList;
    }
    
    public ObservableList<String> getChatList() {
        return chatList;
    }
    
    public StringProperty getTextFieldInput() {
        return textFieldInput;
    }
    
    public void disconnect() {
        model.disconnect();
//        System.exit(0);
    }
    
    public void sendMessage() {
        if (textFieldInput == null ||
            !textFieldInput.get().isEmpty()) {
            model.sendPublicMessage(textFieldInput.get());
        }
        textFieldInput.set("");
    }
    
    public void getUsers() {
        usersList.clear();
        usersList.addAll(model.getOnlineUsersList());
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            Message message = (Message) evt.getNewValue();
            switch (evt.getPropertyName()) {
                case "login":
                case "disconnect":
                    chatList.add(message.getUsername() + " " + message.getText());
                    break;
                case "message":
                    chatList.add(message.getUsername() + ": " + message.getText());
                    break;
            }
        });
    }
}

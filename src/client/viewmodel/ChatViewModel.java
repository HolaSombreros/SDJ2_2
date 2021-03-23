package client.viewmodel;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ChatViewModel implements PropertyChangeListener
{
    private Model model;
    private ObservableList<String> usersList;
    private ObservableList<String> chatList;
    private StringProperty textFieldInput;
    private PropertyChangeSupport property;
    private String currentUser;

    
    public ChatViewModel(Model model) {
        this.model = model;
        this.chatList = FXCollections.observableArrayList();
        this.usersList = FXCollections.observableArrayList();
        this.textFieldInput = new SimpleStringProperty();
    }
    public void reset(){

    }
    public ObservableList<String> getUsersList(){
        return usersList;
    }
    public ObservableList<String> getChatList(){
        return chatList;
    }
    public StringProperty getTextFieldInput(){
        return textFieldInput;
    }
    public void disconnect(){
        model.disconnect();
        System.exit(0);

    }
    public void sendMessage(){
        if(!textFieldInput.get().isEmpty())
        {
            model.sendPublicMessage(textFieldInput.get());
            chatList.add(currentUser + ": " + textFieldInput.get());
        }
        textFieldInput.set("");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        switch (evt.getPropertyName()) {
            case "login":
                // print the newvalue to chat
                usersList.add((String)evt.getNewValue());
                chatList.add((String)evt.getNewValue());
                break;
            case "message":
                chatList.add((String)evt.getNewValue());
                // print the newvalue to chat
                break;
        }
    }
}

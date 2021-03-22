package client.view;

public enum View {
    LOGINVIEW("LoginView.fxml"),
    CHATVIEW("ChatView.fxml");
    
    private String fxmlFile;
    
    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }
    
    public String getFxmlFile() {
        return fxmlFile;
    }
}

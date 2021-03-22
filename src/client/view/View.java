package client.view;

public enum View {
    LOGINVIEW("PasswordGeneratorView.fxml"),
    CHATVIEW("PasswordLogView.fxml");
    
    private String fxmlFile;
    
    View(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }
    
    public String getFxmlFile() {
        return fxmlFile;
    }
}

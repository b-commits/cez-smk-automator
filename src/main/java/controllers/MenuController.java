package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tools.SMKAutomator;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private Button btnSubmit;
    @FXML private PasswordField fldPassword;
    @FXML private TextField fldEmail;
    @FXML private Label lblException;
    private static final String VALIDATION_NOTIFICATION = "Podaj email i hasło";

    private void handleSubmit()  {
        if (!fldEmail.getText().isEmpty() && !fldPassword.getText().isEmpty()) {
            SMKAutomator automator = new SMKAutomator(fldEmail.getText(), fldPassword.getText());
            automator.run();
        } else lblException.setText(VALIDATION_NOTIFICATION);
     }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSubmit.setOnMouseClicked(event -> handleSubmit());
    }
}

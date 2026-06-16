import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void loginUser() {

        String user = usernameField.getText();
        String pass = passwordField.getText();

        // SIMPLE FIXED CREDENTIALS
        if (user.equals("admin") && pass.equals("123")) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root, 600, 500));
                stage.setTitle("Dashboard");
                stage.show();

                // Close login window
                usernameField.getScene().getWindow().hide();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Invalid Login");
            a.setContentText("Wrong username or password!");
            a.show();
        }
    }
}

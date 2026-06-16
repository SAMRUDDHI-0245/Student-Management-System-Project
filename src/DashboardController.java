import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class DashboardController {

    @FXML
    public void openAddStudent(ActionEvent event) {
        switchScene(event, "AddStudent.fxml", "Add Student");
    }

    @FXML
    public void openViewStudents(ActionEvent event) {
        switchScene(event, "StudentsTable.fxml", "View Students");
    }

    @FXML
    public void logout(ActionEvent event) {
        switchScene(event, "Login.fxml", "Login");
    }

    // 🔁 Scene switcher (SINGLE WINDOW)
    private void switchScene(ActionEvent event, String fxml, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle(title);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

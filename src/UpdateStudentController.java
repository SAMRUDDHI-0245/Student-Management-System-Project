import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateStudentController {

    public static Student selectedStudent;

    @FXML private TextField rollField;
    @FXML private TextField nameField;
    @FXML private ComboBox<String> subjectBox;
    @FXML private TextField marksField;

    @FXML
    public void initialize() {

        if (selectedStudent == null) {
            System.out.println("No student received!");
            return;
        }

        rollField.setText(String.valueOf(selectedStudent.getRoll()));
        nameField.setText(selectedStudent.getName());

        subjectBox.getItems().addAll(
                "english",
                "hindi",
                "mathematics",
                "science",
                "sst"
        );
    }

    @FXML
    public void updateMarks() {

        if (subjectBox.getValue() == null || marksField.getText().isEmpty()) {
            showAlert("Please select subject and enter marks");
            return;
        }

        int marks;

        try {
            marks = Integer.parseInt(marksField.getText());
        } catch (NumberFormatException e) {
            showAlert("Marks must be a number");
            return;
        }

        if (marks < 0 || marks > 100) {
            showAlert("Marks must be between 0 and 100");
            return;
        }

        String subject = subjectBox.getValue();
        int roll = selectedStudent.getRoll();

        try {
            Connection conn = DBConnect.getConnection();
            String query = "UPDATE students SET " + subject + " = ? WHERE roll = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, marks);
            pst.setInt(2, roll);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Marks updated successfully!");
            alert.showAndWait();

            marksField.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(msg);
        alert.show();
    }
}

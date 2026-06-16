import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudentController {

    @FXML private TextField rollField;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField courseField;

    // NEW subject fields
    @FXML private TextField englishField;
    @FXML private TextField hindiField;
    @FXML private TextField mathField;
    @FXML private TextField scienceField;
    @FXML private TextField sstField;

    @FXML
    public void addStudent() {
        try {
            int roll = Integer.parseInt(rollField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();

            int english = Integer.parseInt(englishField.getText());
            int hindi = Integer.parseInt(hindiField.getText());
            int math = Integer.parseInt(mathField.getText());
            int science = Integer.parseInt(scienceField.getText());
            int sst = Integer.parseInt(sstField.getText());

            Connection conn = DBConnect.getConnection();

            String query = "INSERT INTO students (roll, name, age, course, english, hindi, mathematics, science, sst) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setInt(1, roll);
            pst.setString(2, name);
            pst.setInt(3, age);
            pst.setString(4, course);
            pst.setInt(5, english);
            pst.setInt(6, hindi);
            pst.setInt(7, math);
            pst.setInt(8, science);
            pst.setInt(9, sst);

            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Student Added Successfully!");
            alert.showAndWait();


            System.out.println("Student Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBack() {
        try {
            javafx.scene.Parent root =
                    javafx.fxml.FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            // OR Menu.fxml (use the actual name of your menu page)

            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setScene(new javafx.scene.Scene(root, 600, 500));
            stage.setTitle("Menu");
            stage.show();

            // close Add Student window
            rollField.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void openTable() {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("StudentsTable.fxml"));
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setScene(new javafx.scene.Scene(root, 650, 600));
            stage.setTitle("Students Table");
            stage.show();

            // Close Add Student window
            rollField.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

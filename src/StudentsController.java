import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentsController {

    @FXML private TableView<Student> table;

    @FXML private TableColumn<Student, Integer> colRoll;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, Integer> colAge;
    @FXML private TableColumn<Student, String> colCourse;

    @FXML private TableColumn<Student, Integer> colEnglish;
    @FXML private TableColumn<Student, Integer> colHindi;
    @FXML private TableColumn<Student, Integer> colMath;
    @FXML private TableColumn<Student, Integer> colScience;
    @FXML private TableColumn<Student, Integer> colSST;

    @FXML private TableColumn<Student, Double> colPercentage;

    @FXML private TextField searchField;

    ObservableList<Student> list = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colRoll.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getRoll()).asObject());
        colName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));
        colAge.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getAge()).asObject());
        colCourse.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCourse()));

        colEnglish.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getEnglish()).asObject());
        colHindi.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getHindi()).asObject());
        colMath.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getMathematics()).asObject());
        colScience.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getScience()).asObject());
        colSST.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getSst()).asObject());

        colPercentage.setCellValueFactory(d ->
            new SimpleDoubleProperty(
                Math.round(d.getValue().getPercentage() * 100.0) / 100.0
            ).asObject()
        );

        loadData();

        searchField.textProperty().addListener((obs, oldVal, newVal) -> filterStudents(newVal));
    }

    @FXML
    public void loadData() {
        list.clear();

        try {
            Connection conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("roll"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course"),
                        rs.getInt("english"),
                        rs.getInt("hindi"),
                        rs.getInt("mathematics"),
                        rs.getInt("science"),
                        rs.getInt("sst")
                ));
            }

            table.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterStudents(String keyword) {

        ObservableList<Student> filtered = FXCollections.observableArrayList();

        for (Student s : list) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())
                    || s.getCourse().toLowerCase().contains(keyword.toLowerCase())
                    || String.valueOf(s.getRoll()).contains(keyword)) {

                filtered.add(s);
            }
        }

        table.setItems(filtered);
    }

    @FXML
    public void updateStudent() {
        Student s = table.getSelectionModel().getSelectedItem();
        if (s == null) return;

        try {
            UpdateStudentController.selectedStudent = s;
            javafx.fxml.FXMLLoader loader =
                    new javafx.fxml.FXMLLoader(getClass().getResource("UpdateStudent.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setScene(new javafx.scene.Scene(root, 400, 500));
            stage.setTitle("Update Student");
            stage.setOnHidden(e -> loadData());
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
public void goBack(javafx.event.ActionEvent event) {
    try {
        Parent root =
                javafx.fxml.FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                .getScene().getWindow();

        stage.setScene(new javafx.scene.Scene(root));
        stage.setTitle("Menu");

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    @FXML
    public void deleteStudent() {
        Student s = table.getSelectionModel().getSelectedItem();
        if (s == null) return;

        try {
            Connection conn = DBConnect.getConnection();
            PreparedStatement pst =
                    conn.prepareStatement("DELETE FROM students WHERE roll=?");
            pst.setInt(1, s.getRoll());
            pst.executeUpdate();

            list.remove(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

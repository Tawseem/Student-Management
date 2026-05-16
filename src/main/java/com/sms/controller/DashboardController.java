package com.sms.controller;

import com.sms.main.MainApp;
import com.sms.model.Student;
import com.sms.service.StudentManager;
import com.sms.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DashboardController {

    @FXML private Label totalStudentsLabel;
    @FXML private Label avgMarksLabel;
    @FXML private Label topScorerLabel;
    @FXML private TextField searchField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colId;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colCourse;
    @FXML private TableColumn<Student, Integer> colSemester;
    @FXML private TableColumn<Student, Integer> colAge;
    @FXML private TableColumn<Student, String> colGender;
    @FXML private TableColumn<Student, Double> colMarks;
    @FXML private TableColumn<Student, String> colPhone;

    private StudentManager studentManager;
    private ObservableList<Student> studentObservableList;

    public void initialize() {
        studentManager = new StudentManager();
        
        // Setup columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        colSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        loadTableData();
        updateStatistics();

        // Search listener
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue);
        });
    }

    private void loadTableData() {
        List<Student> allStudents = studentManager.getAllStudents();
        studentObservableList = FXCollections.observableArrayList(allStudents);
        studentTable.setItems(studentObservableList);
    }

    private void filterTable(String keyword) {
        List<Student> filtered = studentManager.searchStudents(keyword);
        studentTable.setItems(FXCollections.observableArrayList(filtered));
    }

    private void updateStatistics() {
        totalStudentsLabel.setText(String.valueOf(studentManager.getTotalStudents()));
        avgMarksLabel.setText(String.format("%.2f", studentManager.getAverageMarks()));
        Student top = studentManager.getHighestScorer();
        if (top != null) {
            topScorerLabel.setText(top.getName() + " (" + top.getMarks() + ")");
        } else {
            topScorerLabel.setText("N/A");
        }
    }

    @FXML
    private void showAddStudentForm(ActionEvent event) {
        MainApp.showAddStudent();
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Selection Error", "Please select a student to update.");
            return;
        }
        MainApp.showUpdateStudent(selected);
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Selection Error", "Please select a student to delete.");
            return;
        }

        boolean confirm = AlertUtil.showConfirmation("Delete Student", 
            "Are you sure you want to delete student: " + selected.getName() + "?");
        
        if (confirm) {
            studentManager.deleteStudent(selected.getId());
            loadTableData();
            updateStatistics();
            AlertUtil.showInfo("Success", "Student deleted successfully!");
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        MainApp.showLogin();
    }
}

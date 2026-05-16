package com.sms.controller;

import com.sms.main.MainApp;
import com.sms.model.Student;
import com.sms.service.StudentManager;
import com.sms.util.AlertUtil;
import com.sms.util.ValidationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddStudentController {

    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private ComboBox<String> genderCombo;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;
    @FXML private TextField courseField;
    @FXML private TextField semesterField;
    @FXML private TextField marksField;

    private StudentManager studentManager;

    public void initialize() {
        studentManager = new StudentManager();
    }

    @FXML
    private void handleSave(ActionEvent event) {
        if (!validateInput()) {
            return;
        }

        Student student = new Student(
            idField.getText(),
            nameField.getText(),
            Integer.parseInt(ageField.getText()),
            genderCombo.getValue(),
            phoneField.getText(),
            emailField.getText(),
            addressField.getText(),
            courseField.getText(),
            Integer.parseInt(semesterField.getText()),
            Double.parseDouble(marksField.getText())
        );

        if (studentManager.getStudentById(student.getId()) != null) {
            AlertUtil.showError("Validation Error", "Student ID already exists!");
            return;
        }

        studentManager.addStudent(student);
        AlertUtil.showInfo("Success", "Student added successfully!");
        MainApp.showDashboard();
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        MainApp.showDashboard();
    }

    private boolean validateInput() {
        if (idField.getText().isEmpty() || nameField.getText().isEmpty() || courseField.getText().isEmpty() || genderCombo.getValue() == null) {
            AlertUtil.showError("Validation Error", "Please fill in all required fields (ID, Name, Course, Gender).");
            return false;
        }
        
        if (!ValidationUtil.isInteger(ageField.getText())) {
            AlertUtil.showError("Validation Error", "Age must be a valid integer.");
            return false;
        }

        if (!ValidationUtil.isInteger(semesterField.getText())) {
            AlertUtil.showError("Validation Error", "Semester must be a valid integer.");
            return false;
        }

        if (!ValidationUtil.isNumeric(marksField.getText())) {
            AlertUtil.showError("Validation Error", "Marks must be a valid number.");
            return false;
        }

        if (!phoneField.getText().isEmpty() && !ValidationUtil.isValidPhone(phoneField.getText())) {
            AlertUtil.showError("Validation Error", "Phone number must be 10 digits.");
            return false;
        }

        if (!emailField.getText().isEmpty() && !ValidationUtil.isValidEmail(emailField.getText())) {
            AlertUtil.showError("Validation Error", "Invalid email format.");
            return false;
        }

        return true;
    }
}

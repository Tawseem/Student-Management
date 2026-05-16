package com.sms.main;

import com.sms.model.Student;
import com.sms.controller.UpdateStudentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Student Management System");
        showLogin();
    }

    public static void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 400, 400);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/Dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1100, 700);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showAddStudent() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/AddStudent.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 700);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showUpdateStudent(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/UpdateStudent.fxml"));
            Parent root = loader.load();
            
            UpdateStudentController controller = loader.getController();
            controller.setStudentData(student);
            
            Scene scene = new Scene(root, 600, 700);
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

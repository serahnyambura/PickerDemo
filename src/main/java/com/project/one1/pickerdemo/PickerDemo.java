package com.project.one1.pickerdemo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.Period;

public class PickerDemo extends Application {
    private DatePicker datePicker;
    private ColorPicker colorPicker;
    private Text message;

    @Override
    public void start(Stage primaryStage) {
        try {
            initializeComponents();

            HBox pickersBox = createPickersBox();
            VBox root = createRootVBox(pickersBox, message);

            Scene scene = new Scene(root, 400, 150);

            configureStage(primaryStage, scene);

            primaryStage.show();
        } catch (Exception e) {
            handleException("Error occurred during application startup", e);
        }
    }

    private void initializeComponents() {
        try {
            datePicker = new DatePicker(LocalDate.now());
            datePicker.setOnAction(this::processDateChoice);

            colorPicker = new ColorPicker(Color.BLACK);
            colorPicker.setOnAction(this::processColorChoice);

            message = new Text("Greetings, today is on " + LocalDate.now().getDayOfWeek());
            message.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 24));
        } catch (Exception e) {
            handleException("Error occurred during component initialization", e);
        }
    }

    private HBox createPickersBox() {
        try {
            HBox pickersBox = new HBox(datePicker, colorPicker);
            pickersBox.setSpacing(15);
            pickersBox.setAlignment(Pos.CENTER);
            return pickersBox;
        } catch (Exception e) {
            handleException("Error occurred while creating pickers box", e);
            return new HBox(); // Return an empty box in case of an error
        }
    }

    private VBox createRootVBox(HBox pickersBox, Text message) {
        try {
            VBox root = new VBox();
            root.setStyle("-fx-background-color: white");
            root.setSpacing(20);
            root.setAlignment(Pos.CENTER);
            root.getChildren().addAll(pickersBox, message);
            return root;
        } catch (Exception e) {
            handleException("Error occurred while creating root VBox", e);
            return new VBox(); // Return an empty box in case of an error
        }
    }

    private void configureStage(Stage primaryStage, Scene scene) {
        try {
            primaryStage.setTitle("Picker Demo");
            primaryStage.setScene(scene);
        } catch (Exception e) {
            handleException("Error occurred while configuring stage", e);
        }
    }

    private void handleException(String message, Exception exception) {
        System.err.println(message);
        exception.printStackTrace();
    }

    public void processDateChoice(ActionEvent event) {
        try {
            LocalDate birthDate = datePicker.getValue();
            LocalDate currentDate = LocalDate.now();
            int age = Period.between(birthDate, currentDate).getYears();

            Color chosenColor = colorPicker.getValue();
            message.setText("User current age is: " + age);
            message.setFill(chosenColor);
        } catch (Exception e) {
            handleException("Error occurred during date processing", e);
        }
    }

    public void processColorChoice(ActionEvent event) {
        try {
            Color chosenColor = colorPicker.getValue();
            message.setFill(chosenColor);
        } catch (Exception e) {
            handleException("Error occurred during color processing", e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

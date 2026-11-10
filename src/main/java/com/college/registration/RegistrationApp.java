package com.college.registration;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RegistrationApp extends Application {
	
    // fields live at class level so Stage C can read them in the button handlers
    private final TextField idField = new TextField();
    private final TextField nameField = new TextField();
    private final TextField emailField = new TextField();
    private final TextField programField = new TextField();
    private final Label statusLabel = new Label();

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();      // root layout

        root.setTop(buildTop());
        root.setLeft(buildLeft());
        root.setCenter(buildCenter());

        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("College Student Registration");
        stage.setScene(scene);
        stage.show();
    }

    // ---------- TOP: HBox with the application title ----------
    private HBox buildTop() {
    	
    	
        Label title = new Label("College Student Registration");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        HBox top = new HBox(title);
        top.setPadding(new Insets(15));
        top.setAlignment(Pos.CENTER);
        top.setStyle("-fx-background-color: #2c3e50;");
        return top;
    }

    // ---------- LEFT: VBox with the navigation buttons ----------
    private VBox buildLeft() {
        Button dashboardBtn = new Button("Dashboard");
        Button registrationBtn = new Button("Registration");
        Button coursesBtn = new Button("Courses");
        Button helpBtn = new Button("Help");

        // let every button stretch to the same width
        for (Button b : new Button[] { dashboardBtn, registrationBtn, coursesBtn, helpBtn }) {
            b.setMaxWidth(Double.MAX_VALUE);
        }

        VBox left = new VBox(10, dashboardBtn, registrationBtn, coursesBtn, helpBtn);
        left.setPadding(new Insets(15));
        left.setPrefWidth(160);
        left.setStyle("-fx-background-color: #ecf0f1;");
        return left;
    }
    
    // ---------- CENTER: VBox holding the heading, GridPane form, and button row ----------
    private VBox buildCenter() {
        Label heading = new Label("Student Registration Form");
        heading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // GridPane: labels in column 0, input fields in column 1
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Student ID:"), 0, 0);   // (node, column, row)
        form.add(idField, 1, 0);
        form.add(new Label("Full Name:"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Email:"), 0, 2);
        form.add(emailField, 1, 2);
        form.add(new Label("Program:"), 0, 3);
        form.add(programField, 1, 3);

        idField.setPromptText("e.g. 301234567");
        emailField.setPromptText("name@example.com");

        // HBox: Register and Clear side by side
        Button registerBtn = new Button("Register");
        Button clearBtn = new Button("Clear");
        registerBtn.setOnAction(e -> handleRegister());
        clearBtn.setOnAction(e -> handleClear());
        HBox actions = new HBox(10, registerBtn, clearBtn);

        VBox center = new VBox(15, heading, form, actions, statusLabel);
        center.setPadding(new Insets(20));
        return center;
    }

    // ---------- EVENT: Register button ----------
    private void handleRegister() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String program = programField.getText().trim();

        // validation: every field is required
        if (id.isEmpty() || name.isEmpty() || email.isEmpty() || program.isEmpty()) {
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setText("All fields are required.");
            return;                                  // stop here, don't register
        }

        // extra check: the email must at least contain "@"
        if (!email.contains("@")) {
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setText("Please enter a valid email address.");
            return;
        }

        // all good: show confirmation
        statusLabel.setStyle("-fx-text-fill: green;");
        statusLabel.setText("Student " + name + " registered successfully.");
    }

    // ---------- EVENT: Clear button ----------
    private void handleClear() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        programField.clear();
        statusLabel.setText("");
        idField.requestFocus();                      // cursor goes back to the first field
    }
    public static void main(String[] args) {
        launch(args);
    }
}
package me.robnoo.passencryptor;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.robnoo.passencryptor.generators.SimplePasswordGenerator;

/*

Profits:
- Doesn't store passwords
- Unique password for every application
- Only remember one password
- Both desktop and mobile
- Directly copied to clipboard
- Contains lowercase, uppercase, numbers and special characters
- Difficult to hack with bruteforce
- Long passwords (20+ characters)
- Unique algorithm per password <> application combination
- Selfmade, so not publicly available

 */
public class PassEncryptor extends Application {

    private static final double WIDTH = 500, HEIGHT = 350, TEXTFIELD_LABEL_WIDTH = 130;
    private static final String INVALID_PASSWORD = "Invalid password!", INVALID_APP = "Invalid application!";
    private boolean passwordVisible = false;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final TextField application = new TextField();
        final TextField passwordShown = new TextField();
        final PasswordField password = new PasswordField();
        final Button generate = new Button("Generate");
        final Button showPassword = new Button("\uD83D\uDC41");
        final Button generateSimple = new Button("Generate Simplified");
        final Label feedback = new Label();
        final Label passwordLabel = new Label("Password:");
        final Label applicationLabel = new Label("Application:");
        final Label appLabel = new Label("PassEncryptor v1.0");
        final Label creditsLabel = new Label("Copyright © 2020 Robbe Nooyens. All Rights Reserved");

        application.setPromptText("Application");
        application.setPrefWidth(WIDTH - TEXTFIELD_LABEL_WIDTH - 40);
        application.setOnKeyTyped((event -> feedback.setVisible(false)));
        application.setOnMouseClicked((event -> feedback.setVisible(false)));
        application.getStyleClass().add("grey");
        application.setAlignment(Pos.CENTER_LEFT);
        Tooltip applicationTooltip = new Tooltip("Ex. Google, Facebook, Hotmail, Office365, Paypal, ...");
        applicationTooltip.getStyleClass().add("grey");
        application.setTooltip(applicationTooltip);

        password.setPromptText("Password");
        password.setPrefWidth(WIDTH - TEXTFIELD_LABEL_WIDTH - 100);
        password.setOnKeyTyped(event -> feedback.setVisible(false));
        password.setOnMouseClicked(event -> feedback.setVisible(false));
        password.getStyleClass().add("grey");
        password.setAlignment(Pos.CENTER_LEFT);
        Tooltip passwordTooltip = new Tooltip("Allowed characters: {a-z}, {A-Z}, {0-9}, {!\"#$%&()*+-/<=>?@[]_{}}");
        passwordTooltip.getStyleClass().addAll("grey");
        password.setTooltip(passwordTooltip);

        feedback.setVisible(false);
        feedback.setPrefHeight(30);
        feedback.setAlignment(Pos.BOTTOM_CENTER);
        feedback.getStyleClass().add("grey");

        generate.getStyleClass().add("grey");
        generate.setOnAction((event) -> {
            if(passwordVisible) {
                if (passwordShown.getText() == null || passwordShown.getText().length() < 1) {
                    feedback.setText(INVALID_PASSWORD);
                    feedback.setVisible(true);
                    return;
                }
            } else {
                if (password.getText() == null || password.getText().length() < 1) {
                    feedback.setText(INVALID_PASSWORD);
                    feedback.setVisible(true);
                    return;
                }
            }
            if(application.getText() == null || application.getText().length() < 1) {
                feedback.setText(INVALID_APP);
                feedback.setVisible(true);
                return;
            }
            final SimplePasswordGenerator passwordGenerator = new SimplePasswordGenerator(passwordVisible ? passwordShown.getText() : password.getText(), application.getText());
            String generated = passwordGenerator.generate();
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(generated);
            content.putHtml(generated);
            clipboard.setContent(content);
            feedback.setText("Copied password to clipboard!");
            feedback.setVisible(true);
        });

        generateSimple.getStyleClass().add("grey");
        generateSimple.setOnAction((event) -> {
            if(passwordVisible) {
                if (passwordShown.getText() == null || passwordShown.getText().length() < 1) {
                    feedback.setText(INVALID_PASSWORD);
                    feedback.setVisible(true);
                    return;
                }
            } else {
                if (password.getText() == null || password.getText().length() < 1) {
                    feedback.setText(INVALID_PASSWORD);
                    feedback.setVisible(true);
                    return;
                }
            }
            if(application.getText() == null || application.getText().length() < 1) {
                feedback.setText(INVALID_APP);
                feedback.setVisible(true);
                return;
            }
            final SimplePasswordGenerator passwordGenerator = new SimplePasswordGenerator(passwordVisible ? passwordShown.getText() : password.getText(), application.getText());
            String generated = passwordGenerator.generateSimple();
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(generated);
            content.putHtml(generated);
            clipboard.setContent(content);
            feedback.setText("Copied simplified password to clipboard!");
            feedback.setVisible(true);
        });
        Tooltip generateSimpleTooltip = new Tooltip("Only use this if the app doesn't allow special characters");
        generateSimpleTooltip.getStyleClass().add("grey");
        generateSimple.setTooltip(generateSimpleTooltip);

        showPassword.getStyleClass().addAll("grey");
        showPassword.setPrefWidth(60);
        showPassword.setOnAction((event) -> {
            passwordVisible = !passwordVisible;
            password.setVisible(!passwordVisible);
            //showPassword.setText(passwordVisible ? "\uD83D\uDC41" : "\uD83D\uDC41");
            if(passwordVisible && password.getText() != null)
                passwordShown.setText(password.getText());
            else if(!passwordVisible && passwordShown.getText() != null)
                password.setText(passwordShown.getText());
        });

        passwordShown.getStyleClass().add("grey");
        passwordShown.setOnKeyTyped((event -> feedback.setVisible(false)));
        passwordShown.setOnMouseClicked((event -> feedback.setVisible(false)));

        creditsLabel.getStyleClass().addAll("grey", "credits");
        creditsLabel.setAlignment(Pos.BOTTOM_CENTER);
        appLabel.getStyleClass().addAll("grey", "credits");
        appLabel.setAlignment(Pos.BOTTOM_CENTER);

        passwordLabel.getStyleClass().add("grey");
        passwordLabel.setPrefWidth(TEXTFIELD_LABEL_WIDTH);
        passwordLabel.setAlignment(Pos.CENTER_LEFT);

        applicationLabel.getStyleClass().add("grey");
        applicationLabel.setPrefWidth(TEXTFIELD_LABEL_WIDTH);
        applicationLabel.setAlignment(Pos.CENTER_LEFT);

        VBox layout = new VBox();
        layout.setPadding(new Insets(20,20,20,20));
        layout.setMaxHeight(HEIGHT);
        layout.setPrefHeight(HEIGHT);
        layout.setMaxWidth(WIDTH);
        layout.setPrefWidth(WIDTH);
        layout.setAlignment(Pos.CENTER);

        StackPane passwordToggle = new StackPane();
        passwordToggle.getChildren().addAll(passwordShown, password);

        HBox passwordFields = new HBox();
        passwordFields.getChildren().addAll(passwordLabel, passwordToggle, showPassword);
        passwordFields.setMaxWidth(WIDTH);
        passwordFields.setAlignment(Pos.CENTER);

        HBox applicationFields = new HBox();
        applicationFields.getChildren().addAll(applicationLabel, application);
        applicationFields.setMaxWidth(WIDTH);
        applicationFields.setAlignment(Pos.CENTER);

        VBox inputFields = new VBox();
        inputFields.getChildren().addAll(passwordFields, applicationFields);
        inputFields.setSpacing(0);
        inputFields.setAlignment(Pos.CENTER);

        VBox generateButtons = new VBox();
        generateButtons.getChildren().addAll(generate, generateSimple);
        generateButtons.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(inputFields, generateButtons, feedback);
        layout.getStyleClass().add("grey");
        layout.setSpacing(10);

        VBox bottom = new VBox();
        bottom.getChildren().addAll(appLabel, creditsLabel);
        bottom.getStyleClass().add("grey");
        bottom.setAlignment(Pos.CENTER);

        BorderPane content = new BorderPane();
        content.setCenter(layout);
        content.setBottom(bottom);
        BorderPane.setAlignment(creditsLabel, Pos.CENTER);
        content.getStyleClass().add("grey");
        //content.setAlignment(Pos.CENTER);

        Scene scene = new Scene(content, WIDTH, HEIGHT);
        scene.setFill(Color.web("#696969"));
        scene.getStylesheets().add("application.css");
        scene.setOnMouseClicked(event -> feedback.setVisible(false));

        primaryStage.setTitle("Password Encryption Tool");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}

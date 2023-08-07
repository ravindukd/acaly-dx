/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.iitprojects.acaly.dx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author ravin
 */
public class TaskController implements Initializable {

    private int currentStep = 0;

    @FXML
    private VBox stepList;

    @FXML
    private Label titleLbl;

    @FXML
    private Label subTitleLbl;

    @FXML
    private Label priceLbl;

    @FXML
    private Label creatorLbl;

    @FXML
    private VBox titleBg;

    @FXML
    private AnchorPane headerBg;

    @FXML
    private Accordion stepAcc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        messages.add(new ChatMessage("system", TaskDao.selectedTask.description));
        setTaskUI();
    }

    private void setTaskUI() {
        titleLbl.setText(TaskDao.selectedTask.title);
        subTitleLbl.setText(TaskDao.selectedTask.description);
        titleBg.setStyle("-fx-background-color:" + TaskDao.selectedTask.bgColor + ";");
        headerBg.setStyle("-fx-background-color:" + TaskDao.selectedTask.bgColor + ";");
        this.loadSteps();
    }

    @FXML
    private void closeUI() throws IOException {
        App.setRoot("home");
    }

    List<ChatMessage> messages = new ArrayList<>();

    private void executeStep(String prompt, int sid) throws IOException {
        TitledPane activeTitledPane = stepAcc.getPanes().get(currentStep);

        AnchorPane activeAnchorPane = (AnchorPane) activeTitledPane.getContent();
        TextArea instructionTextArea = (TextArea) activeAnchorPane.getChildren().get(1);
        System.out.println(instructionTextArea);
        String text = instructionTextArea.getText().trim().replace("\n", " ");

        messages.add(new ChatMessage("user", prompt + text));

        String resp = ChatGpt3ApiClient.callGpt3ChatApi(messages);
        String con = ChatGpt3ApiClient.extractFirstContent(resp);
        System.out.println(con);

        currentStep = sid + 1;
        if (!(currentStep >= 0 && currentStep < stepAcc.getPanes().size())) {
            currentStep = 0;
        }
        stepAcc.setExpandedPane(stepAcc.getPanes().get(currentStep));
        TitledPane activeTitledPane2 = stepAcc.getPanes().get(currentStep);

        AnchorPane activeAnchorPane2 = (AnchorPane) activeTitledPane2.getContent();

        TextArea instructionTextArea2 = (TextArea) activeAnchorPane2.getChildren().get(1);
        instructionTextArea2.setText(con);

    }

    @FXML
    private void loadSteps() {
        List<Step> steps = TaskDao.getAllSteps(TaskDao.selectedTask.id);

        for (Step step : steps) {
            AnchorPane anchorPane = new AnchorPane();

            Label descriptionLabel = new Label(step.description);
            descriptionLabel.setFont(Font.font("System", FontWeight.BOLD, 13));
            AnchorPane.setLeftAnchor(descriptionLabel, 16.0);
            AnchorPane.setRightAnchor(descriptionLabel, 16.0);
            AnchorPane.setTopAnchor(descriptionLabel, 8.0);
            anchorPane.getChildren().add(descriptionLabel);

            TextArea instructionTextArea = new TextArea();
            instructionTextArea.setPromptText(step.instruciton);
            instructionTextArea.setWrapText(true);
            AnchorPane.setBottomAnchor(instructionTextArea, 64.0);
            AnchorPane.setLeftAnchor(instructionTextArea, 16.0);
            AnchorPane.setRightAnchor(instructionTextArea, 16.0);
            AnchorPane.setTopAnchor(instructionTextArea, 36.0);
            anchorPane.getChildren().add(instructionTextArea);

            Button continueButton = new Button("Continue");
            AnchorPane.setBottomAnchor(continueButton, 16.0);
            AnchorPane.setLeftAnchor(continueButton, 16.0);
            AnchorPane.setRightAnchor(continueButton, 16.0);
            anchorPane.getChildren().add(continueButton);
            continueButton.setOnAction(event -> {
                try {
                    executeStep(step.prompt, step.id - 1);
                } catch (IOException ex) {
                    Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            TitledPane titledPane = new TitledPane("Step " + step.id + " : " + step.title, anchorPane);
            titledPane.setAnimated(true);
            stepAcc.getPanes().add(titledPane);
            stepAcc.setExpandedPane(stepAcc.getPanes().get(currentStep));

        }

    }

}

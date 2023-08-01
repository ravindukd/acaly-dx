/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.iitprojects.acaly.dx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ravin
 */
public class TaskController implements Initializable {

    @FXML
    private VBox stepList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    @FXML
    private void loadSteps(){
        AnchorPane anchorPane = new AnchorPane();

        // First Label
        Label stepTitleLabel = new Label("1. Step Title");
        stepTitleLabel.setMaxWidth(100.0);
        AnchorPane.setBottomAnchor(stepTitleLabel, 16.0);
        AnchorPane.setLeftAnchor(stepTitleLabel, 16.0);
        AnchorPane.setTopAnchor(stepTitleLabel, 16.0);
        anchorPane.getChildren().add(stepTitleLabel);

        // Second Label
        Label descriptionLabel = new Label("ssada da d ad a d a sd as d as d as da s da sd a sd as das d");
        descriptionLabel.setMaxWidth(200.0);
        AnchorPane.setBottomAnchor(descriptionLabel, 16.0);
        AnchorPane.setRightAnchor(descriptionLabel, 16.0);
        AnchorPane.setTopAnchor(descriptionLabel, 16.0);
        anchorPane.getChildren().add(descriptionLabel);

        anchorPane.getStyleClass().add("stepTile");
        anchorPane.setMaxWidth(500);
//        anchorPane.getStylesheets().add(getClass().getResource("../../../../styles/task.css").toExternalForm());
//        
        stepList.getChildren().add(anchorPane);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.iitprojects.acaly.dx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ravin
 */
public class HomeController implements Initializable {

    @FXML
    VBox categoriesVbox;
    
    @FXML
    private Accordion filtersAccord;
    
    @FXML
    private TitledPane catPane;
    @FXML
    private TitledPane pricePane;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filtersAccord.setExpandedPane(catPane);
    }    
    
    @FXML
    private void addToCatVbox(){
        Button catBtn = new Button("♒ All");
        catBtn.setMaxWidth(Double.MAX_VALUE);
        catBtn.setAlignment(Pos.TOP_LEFT);
        catBtn.getStyleClass().add("catListItem");
        categoriesVbox.getChildren().add(catBtn);
    }
}

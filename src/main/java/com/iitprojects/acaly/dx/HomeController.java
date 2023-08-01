/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.iitprojects.acaly.dx;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author ravin
 */
public class HomeController implements Initializable {

    @FXML
    private VBox categoriesVbox;

    @FXML
    private Accordion filtersAccord;

    @FXML
    private TitledPane catPane;
    @FXML
    private TitledPane pricePane;

    @FXML
    private GridPane taskGrid;

    @FXML
    private ProgressIndicator loading;

    @FXML
    private Label titleLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filtersAccord.setExpandedPane(catPane);
        this.getData();
        this.loadTasks(0, "All");
        loading.setVisible(false);
    }

    @FXML
    private void addToCatVbox() {
        Button catBtn = new Button("♒ All");
        catBtn.setMaxWidth(Double.MAX_VALUE);
        catBtn.setAlignment(Pos.TOP_LEFT);
        catBtn.getStyleClass().add("catListItem");
        categoriesVbox.getChildren().add(catBtn);
    }

    @FXML
    private void getData() {
        loading.setVisible(true);
        try {
            List<Category> categories = TaskDao.getAllCategories();

            for (Category cat : categories) {
                Button taskBtn = new Button(cat.getName());
                taskBtn.setMaxWidth(Double.MAX_VALUE);
                taskBtn.setAlignment(Pos.TOP_LEFT);
                taskBtn.getStyleClass().add("catListItem");
                taskBtn.setOnAction(event -> loadTasks(cat.id, cat.name));
                categoriesVbox.getChildren().add(taskBtn);
            }
            loading.setVisible(false);

        } catch (SQLException ex) {
            loading.setVisible(false);

            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        loading.setVisible(false);

    }

    @FXML
    private void loadTasks(int catId, String title) {
        loading.setVisible(true);

        titleLbl.setText(title + " Tasks");

        try {
            List<Task> tasks = TaskDao.getAllTasks(catId);
            int size = Math.min(tasks.size(), 25);
            int rows = (int) Math.ceil(size / 3.0);

            taskGrid.getChildren().clear();

            if (tasks.isEmpty()) {
                Pane pn = new Pane();
                pn.getStyleClass().add("taskTile");

                VBox vBox = new VBox();
                vBox.setAlignment(Pos.CENTER);
                vBox.getStyleClass().add("taskTile");

                Label t = new Label("No Tasks");
                t.setFont(Font.font(18));

                Label s = new Label("E:404");
                s.setFont(Font.font(12));

                vBox.getChildren().addAll(t, s);

                pn.getChildren().add(vBox);
                taskGrid.add(vBox, 0, 0, 1, 1);
            } else {

                for (int i = 0, c = 0; i < rows; i++) {
                    for (int j = 0; j < 3 && c < size; j++, c++) {
                        try {
                            Task task = tasks.get(c);

                            Pane pn = new Pane();
                            pn.getStyleClass().add("taskTile");

                            VBox vBox = new VBox();
                            vBox.setAlignment(Pos.CENTER);
                            vBox.getStyleClass().add("taskTile");

                            Label t = new Label(task.getTitle());
                            t.setFont(Font.font(18));

                            Label s = new Label(task.getTitle());
                            s.setFont(Font.font(12));

                            Button b = new Button("Select");
                            
                            b.setOnAction(event -> {
                                try {
                                    TaskDao.selectedID = task.id;
                                    App.setRoot("task-home");
                                } catch (IOException e) {
                                    System.out.println("Load Error: " + e.getMessage());
                                }
                            });
                            vBox.getChildren().addAll(t, s, b);

                            pn.getChildren().add(vBox);
                            taskGrid.add(vBox, j, i, 1, 1);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }

            loading.setVisible(false);

        } catch (SQLException ex) {
            loading.setVisible(false);

        }
        loading.setVisible(false);

    }
}

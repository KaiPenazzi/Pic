package ViewModel;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GridPaneController {

    @FXML
    private GridPane GridPane;

    @FXML
    private Button Button1;
    
    @FXML
    private ScrollPane scrollPane;

    @FXML
    void clickButton(ActionEvent event) {
        scrollPane.setContent(createTextBoxGrid());
        
        Pane newPane;
        try {
            newPane = FXMLLoader.load(getClass().getClassLoader().getResource("View/test.fxml"));
            setGridPane(newPane, 0, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGridPane(Node node, int x, int y){
        GridPane.add(node, x, y);
    }

    private GridPane createTextBoxGrid(){
        TextField[] field = new TextField[255];
        GridPane newGridPane = new GridPane();

        int j = 0;
        int k = 0;
        for (int i = 0; i < field.length; i++) {
            field[i] = new TextField();
            field[i].setMaxWidth(40);
            newGridPane.add(field[i], j, k);
            
            j++;
            if (9 < j){
                k++;
                j = 0;
            }
            
        }

        return newGridPane;
    }
}
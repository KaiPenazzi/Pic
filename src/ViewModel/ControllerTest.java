package ViewModel;

import java.util.ArrayList;

import converter.tools;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerTest {

    tools theTool = new tools();
    ArrayList<String> commandList = new ArrayList<String>();

    @FXML
    private Button ButtonOne;

    @FXML
    private TextField TextFieldOne;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textFieldHex;

    @FXML
    private Button button2;

    @FXML
    void clickButton(ActionEvent event) {
        //System.out.println(TextFieldOne.getText());
        // textField2.setText("" + theTool.hexToDecimal(TextFieldOne.getText())); 
        // textFieldHex.setText(theTool.hexToBinary(TextFieldOne.getText()));

    }

    @FXML
    void clickButton2(ActionEvent event) {
        // theTool.readFile("doc\\TPicSim1.LST", commandList);

        for (String command : commandList) {
            System.out.println(command);
        }

        

    }

}
package ViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SFRAndWViewModel {

    @FXML
    private Label labelWRegisterValue;

    @FXML
    private Label labelPCLValue;

    @FXML
    private Label labelPCLathValue;

    @FXML
    private Label labelPCinternValue;

    @FXML
    private Label labelStatusValue;

    @FXML
    private Label labelFSRValue;

    @FXML
    private Label labelOptionValue;

    @FXML
    private Label labelVorteilerValue;

    @FXML
    private Label labelTimer0Value;

    public void initialize() {
        
    }

    public void setLabelWRegisterValue(int Value){
        labelWRegisterValue.setText("" + Value);
    }

}

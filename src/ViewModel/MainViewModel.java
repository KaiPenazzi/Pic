package ViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.plaf.TextUI;

import Model.Ports;
import Model.ProgramCounter;
import Model.RAM;
import Model.Stacktool;
import Model.Statusregister;
import Model.ThreadParams;
import Model.ThreadWatchdog;
import Model.Timer;
import Model.TimerReturn;
import converter.tools;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class MainViewModel {
    /*
     * Microcontroller Componets
     */

    RAM ram = new RAM();
    Stacktool stack = new Stacktool();
    Statusregister SR = new Statusregister();
    Timer timer = new Timer();

    Ports portA0 = new Ports(0, 0);
    Ports portB4 = new Ports(0, 0);
    Ports portB5 = new Ports(0, 0);
    Ports portB6 = new Ports(0, 0);
    Ports portB7 = new Ports(0, 0);

    ArrayList<String> programmCode;

    String path = "doc/TestPrograms/TPicSim7.LST";

    ProgramCounter programmCounter;

    Boolean theBreak = false;

    int labelCheckBoxSize = 16;
    int lastCommand = 0;

    Thread watchdog;

    boolean timerEnable = false;

    // boolean zeroFlag = false;
    // boolean digitFlag = false;
    // boolean carryFlag = false;

    @FXML
    TextField[] field = new TextField[256];
    CheckBox[] checkBoxDebug;

    ThreadParams threadParams;

    @FXML
    private Pane mainWindow;

    @FXML
    private GridPane gridPane1;

    @FXML
    private GridPane gridPane2_1;

    @FXML
    private GridPane gridPane3_1;

    @FXML
    private CheckBox checkBoxTris0ValueA;

    @FXML
    private CheckBox checkBoxTris1ValueA;

    @FXML
    private CheckBox checkBoxTris2ValueA;

    @FXML
    private CheckBox checkBoxTris3ValueA;

    @FXML
    private CheckBox checkBoxTris4ValueA;

    @FXML
    private CheckBox checkBoxTris5ValueA;

    @FXML
    private CheckBox checkBoxTris6ValueA;

    @FXML
    private CheckBox checkBoxTris7ValueA;

    @FXML
    private CheckBox checkBoxPin0ValueA;

    @FXML
    private CheckBox checkBoxPIn1ValueA;

    @FXML
    private CheckBox checkBoxPIn2ValueA;

    @FXML
    private CheckBox checkBoxPin3ValueA;

    @FXML
    private CheckBox checkBoxPin4ValueA;

    @FXML
    private CheckBox checkBoxTris0ValueB;

    @FXML
    private CheckBox checkBoxTris1ValueB;

    @FXML
    private CheckBox checkBoxTris2ValueB;

    @FXML
    private CheckBox checkBoxTris3ValueB;

    @FXML
    private CheckBox checkBoxTris4ValueB;

    @FXML
    private CheckBox checkBoxTris5ValueB;

    @FXML
    private CheckBox checkBoxTris6ValueB;

    @FXML
    private CheckBox checkBoxTris7ValueB;

    @FXML
    private CheckBox checkBoxPin0ValueB;

    @FXML
    private CheckBox checkBoxPIn1ValueB;

    @FXML
    private CheckBox checkBoxPIn2ValueB;

    @FXML
    private CheckBox checkBoxPin3ValueB;

    @FXML
    private CheckBox checkBoxPin4ValueB;

    @FXML
    private CheckBox checkBoxPin5ValueB;

    @FXML
    private CheckBox checkBoxPin6ValueB;

    @FXML
    private CheckBox checkBoxPin7ValueA;

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonStep;

    @FXML
    private Button buttonStop;

    @FXML
    private Button buttonReset;

    @FXML
    private CheckBox checkBoxWatchdog;

    @FXML
    private Label labelRuntime;

    @FXML
    private TextField textFieldQuarz;

    @FXML
    private Label labelWatchdog;

    @FXML
    private Pane paneDebugCheckboxs;

    @FXML
    private GridPane gridPaneTextArea;

    @FXML
    private GridPane gridPane2_0;

    @FXML
    private GridPane gridPane3_0;

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

    @FXML
    private TextField labelStack0Value;

    @FXML
    private TextField labelStack1Value;

    @FXML
    private TextField labelStack2Value;

    @FXML
    private TextField labelStack3Value;

    @FXML
    private TextField labelStack4Value;

    @FXML
    private TextField labelStack5Value;

    @FXML
    private TextField labelStack6Value;

    @FXML
    private TextField labelStack7Value;

    @FXML
    private Label labelIRPValue;

    @FXML
    private Label labelRP1Value;

    @FXML
    private Label labelRP0Value;

    @FXML
    private Label labelToValue;

    @FXML
    private Label labelPDValue;

    @FXML
    private Label labelZValue;

    @FXML
    private Label labelDCValue;

    @FXML
    private Label labelCValue;

    @FXML
    private Label labelRPuValue;

    @FXML
    private Label labelIEgValue;

    @FXML
    private Label labelTCsValue;

    @FXML
    private Label labelTSeValue;

    @FXML
    private Label labelPSAValue;

    @FXML
    private Label labelPS2Value;

    @FXML
    private Label labelPS1Value;

    @FXML
    private Label labelPS0Value;

    @FXML
    private Label labelGIEValue;

    @FXML
    private Label labelEIE;

    @FXML
    private Label labelTIEValue;

    @FXML
    private Label labelIEValue;

    @FXML
    private Label labelRIEValue;

    @FXML
    private Label labelTIFValue;

    @FXML
    private Label labelIFValue;

    @FXML
    private Label labelRIFValue;

    @FXML
    private ScrollPane scrollPaneRAM;

    @FXML
    private TextArea textAreaMain;

    @FXML
    void buttonResetClick(ActionEvent event) {
        reset();
    }

    @FXML
    void buttonStartClick(ActionEvent event) {
        theBreak = false;
        for (int i = 0; !theBreak && !checkBoxDebug[programmCounter.getCounter()].isSelected(); i++) {
            if (programmCounter.getCounter() < programmCode.size()) {
                decoder(programmCode.get(programmCounter.getCounter()));
                setCheckBoxByCounter(programmCounter.getCounter());
                incPCounter();
            }
        }
    }

    @FXML
    void buttonStepClick(ActionEvent event) {
        if (programmCounter.getCounter() < programmCode.size()) {
            decoder(programmCode.get(programmCounter.getCounter()));
            setCheckBoxByCounter(programmCounter.getCounter());
            incPCounter();
        }
    }

    private void setCheckBoxByCounter(int val) {
        checkBoxDebug[programmCounter.getCounter()].setText("--");
        checkBoxDebug[programmCounter.getCounter()].setTextFill(Paint.valueOf("RED"));
        if (lastCommand >= 0 && lastCommand != val) {
            checkBoxDebug[lastCommand].setText("");
            checkBoxDebug[lastCommand].setTextFill(Paint.valueOf("BLACK"));
        }

        lastCommand = val;
    }

    @FXML
    void buttonStopClick(ActionEvent event) {
        theBreak = true;
    }

    @FXML
    void checkBoxWatchdogClick(ActionEvent event) {
        // if (checkBoxWatchdog.isSelected()){

        // if (!textFieldQuarz.getText().equals("")) {
        // threadParams = new ThreadParams(Integer.parseInt(textFieldQuarz.getText()),
        // ram.getOptionRegister());
        // }
        // else {
        // threadParams = new ThreadParams(1, ram.getOptionRegister());
        // }

        // watchdog = new ThreadWatchdog(threadParams);
        // watchdog.start();
        // }
    }

    @FXML
    void checkBox0PartAClick(ActionEvent event) {

    }

    public void initialize() {
        scrollPaneRAM.setContent(CreateRamGrid());
        paneDebugCheckboxs.getChildren().add(CreateDebugGrid(tools.countLine(path)));
        // paneDebugCheckboxs.getChildren().add(CreateDebugGrid());
        textAreaMain.setPrefHeight(tools.countLine(path) * 17);
        textAreaMain.setText(tools.getFullFile(path));
        programmCode = tools.readFile(path);
        syncronizeRAMwithUI();
        programmCounter = new ProgramCounter();
        initializeRam();
    }

    private GridPane CreateRamGrid() {
        GridPane newGridPane = new GridPane();

        int j = 0;
        int k = 0;
        int i = 0;
        while (i < field.length) {
            if (j != 0 && k != 0) {
                field[i] = new TextField();
                field[i].setMaxSize(30, 15);
                newGridPane.add(field[i], j, k);
                i++;
            } else {
                if (k == 0) {
                    if (j != 0) {
                        Label label = new Label();
                        label.setText(tools.decimalToHex(j - 1));
                        newGridPane.add(label, j, k);
                    }
                } else {
                    if (j == 0) {
                        Label label = new Label();
                        label.setText(tools.decimalToHex((k - 1) * 8));
                        newGridPane.add(label, j, k);
                    }
                }
            }

            j++;
            if (8 < j) {
                k++;
                j = 0;
            }

        }

        return newGridPane;
    }

    private GridPane CreateDebugGrid(int anzCheckboxDebug) {
        checkBoxDebug = new CheckBox[anzCheckboxDebug];

        int indexCheckbox = 0;
        GridPane newGridPane = new GridPane();
        File file = new File(path);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (Exception e) {

        }
        String line = "";
        for (int i = 0; i < anzCheckboxDebug && line != null; i++) {
            try {
                line = br.readLine();
                if (line != null && !line.substring(5, 9).equals("    ")) {
                    checkBoxDebug[indexCheckbox] = new CheckBox();
                    checkBoxDebug[indexCheckbox].setPrefSize(labelCheckBoxSize, labelCheckBoxSize);
                    checkBoxDebug[indexCheckbox].setMinSize(labelCheckBoxSize, labelCheckBoxSize);
                    checkBoxDebug[indexCheckbox].setMaxSize(labelCheckBoxSize, labelCheckBoxSize);

                    newGridPane.add(checkBoxDebug[indexCheckbox], 0, i);
                    indexCheckbox++;
                } else {
                    Label newLabel = new Label();
                    newLabel.setPrefSize(labelCheckBoxSize, labelCheckBoxSize);
                    newLabel.setMaxSize(labelCheckBoxSize, labelCheckBoxSize);
                    newLabel.setMinSize(labelCheckBoxSize, labelCheckBoxSize);
                    newGridPane.add(newLabel, 0, i);
                }
            } catch (Exception e) {

            }
        }
        return newGridPane;
    }

    private void setfield(String data, int pos, int bank) {

        if (pos == 2 && programmCounter != null) {
            programmCounter.setPCL(tools.hexToDecimal(data));
        }

        if (pos < 128) {
            if (bank == 0) {
                field[pos].setText(data);
                ram.setBank(pos, tools.hexToDecimal(data), 0);

                if (pos == 1 && tools.hexToDecimal(data) > 0) {
                    timer.resetVorteiler();
                    labelVorteilerValue.setText(tools.decimalToHex(timer.getVorteiler()));
                }


            } else {
                field[128 + pos].setText(data);
                ram.setBank(pos, tools.hexToDecimal(data), 1);
                if (pos == 1) {
                    timerEnable = false;
                }
            }
        }
        else if (pos > 127 && pos < 256) {
            field[pos].setText(data);
            ram.setBank(pos - 128, tools.hexToDecimal(data), 1);
        }
    }

    private void syncronizeRAMwithUI() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 128; j++) {
                int temp = ram.getBank(j, i);
                setfield(tools.decimalToHex(0), j, i);
            }
        }
    }

    private void saveCommandResult(String address, int value) {
        if (address.charAt(0) == '1') {
            setfield(tools.decimalToHex(value), tools.binaryToDecimal(address.substring(1, address.length())),
                    ram.getChosenBank());
        } else {
            labelWRegisterValue.setText(tools.decimalToHex(value));
        }
    }

    private void decoder(String command) {
        String theCommand = command.substring(0, 2);
        String theAddress = command.substring(2, 4);
        
        if (theAddress.equals("80")) {
            theAddress = labelFSRValue.getText();
            theAddress = tools.decimalToHex(tools.hexToDecimal(theAddress) | 0b10000000);
            
        }
        if (theAddress.equals("00")) {
            theAddress = labelFSRValue.getText();
        }
        command = theCommand + theAddress;

        //resetFlags();
        WatchdogStart();

        TimerReturn timertemp = timer.timerUP(ram.getRegisterByAddress(tools.hexToDecimal("81")),ram.getRegisterByAddress(tools.hexToDecimal("01")), theCommand, ram);
        setfield(tools.decimalToHex(ram.getBank(tools.hexToDecimal("0B"), ram.getChosenBank())), tools.hexToDecimal("0B"), ram.getChosenBank());
        setTimer(timertemp.timer);

        switch (theCommand) {
            // BYTE-ORIENTED FILE REGISTER OPERATIONS
            case "07":// ADDWF
                ADDWF(tools.hexToBinary(theAddress));
                break;

            case "05":// ANDWF
                ANDWF(tools.hexToBinary(theAddress));
                break;

            case "01":// CLRF & CLRW
                if (tools.hexToBinary(theAddress).charAt(0) == '1') {
                    CLRF(tools.hexToBinary(theAddress));
                } else {
                    CLRW(tools.hexToBinary(theAddress));
                }
                break;

            case "09":// COMF
                COMF(tools.hexToBinary(theAddress));
                break;

            case "03":// DECF
                DECF(tools.hexToBinary(theAddress));
                break;

            case "0B":// DECFSZ
                DECFSZ(tools.hexToBinary(theAddress));
                break;

            case "0A":// INCF
                INCF(tools.hexToBinary(theAddress));
                break;

            case "0F":// INCFSZ
                INCFSZ(tools.hexToBinary(theAddress));
                break;

            case "04":// IORWF
                IORWF(tools.hexToBinary(theAddress));
                break;

            case "08":// MOVF
                if (ram.getBank(tools.binaryToDecimal(tools.hexToBinary(theAddress).substring(1, tools.hexToBinary(theAddress).length())), ram.getChosenBank()) == 0) {
                    setZeroFlag(1);
                }
                MOV(tools.hexToBinary(theAddress));
                break;

            case "00":// MOVWF
                MOVWF(tools.hexToBinary(theAddress));
                break;

            case "0D":// RLF
                RLF(tools.hexToBinary(theAddress));
                break;

            case "0C":// RRF
                RRF(tools.hexToBinary(theAddress));
                break;

            case "02":// SUBWF
                SUBWF(tools.hexToBinary(theAddress));
                break;

            case "0E":// SWAPF
                SWAPF(tools.hexToBinary(theAddress));
                break;

            case "06":// XORWF
                XORWF(tools.hexToBinary(theAddress));
                break;

            // BIT-ORIENTED FILE REGISTER OPERATIONS
            // in default

            // LITERAL AND CONTROL OPERATIONS

            case "3E":// ADDLW
                ADDLW(theAddress);
                break;

            case "39":// ANDLW
                ANDLW(tools.hexToBinary(theAddress));
                break;

            // CALL is in default

            // CLRWDT is in case "00" mit abgedeckt

            // GOTO is in default

            case "38":// IORLW
                IORLW(tools.hexToBinary(theAddress));
                break;

            case "30":// MOVLW
                MOVLW(theAddress);
                break;

            // RETFIE is in case "00" mit abgedeckt

            case "34":// RETLW
                RETLW(theAddress);
                break;

            // RETURN is in case "00" mit abgedeckt

            // SLEEP is in case "00" mit abgedeckt

            case "3C":// SUBLW
                SUBLW(theAddress);
                break;

            case "3A":// XORLW
                XORLW(tools.hexToBinary(theAddress));
                break;

            default:
                if (theCommand.charAt(0) == '2') {
                    int temp = tools.hexToDecimal("" + theCommand.charAt(1)) >> 3;
                    if (temp == 1) {
                        // GOTO
                        GOTO(command);
                    } else {
                        // CALL
                        CALL(command);
                    }
                } else if (theCommand.charAt(0) == '1') {
                    int temp = tools.hexToDecimal("" + theCommand.charAt(1)) >> 2;
                    switch (temp) {
                        case 0:// BCF
                            BCF(tools.hexToBinary(command));
                            break;

                        case 1: // BSF
                            BSF(tools.hexToBinary(command));
                            break;

                        case 2:// BTFSC
                            BTFSC(tools.hexToBinary(command));
                            break;

                        case 3:// BTFSS
                            BTFSS(tools.hexToBinary(command));
                            break;

                        default:
                            break;
                    }
                }
                break;
        }
        // stopWatchdog

        if (timertemp.overflow) {
            checkInterrupt(ram.getBank(tools.hexToDecimal("0B"), ram.getChosenBank()));
        }
        
        updateAll();

        if (threadParams == null) {
            // do nothing
        } else if (threadParams.ret) {
            //watchdog.stop();
            reset();
        } else {
            stopWatchdog();
        }
    }

    private void checkInterrupt(int intcons){
        String intconbits = tools.hexToBinary(tools.decimalToHex(intcons)); //7 5 2

        if(intconbits.charAt(0) == '1' && intconbits.charAt(2) == '1' && intconbits.charAt(5) == '1'){
            programmCounter.setCounter(3);
            
        }
    }

    private void updateAll() {
        //updateTris();
        updateSFR();
        updateSFRW();
        checktTrisReg();
    }

    private void ADDWF(String address) {
        int temp = 0;

        setCarryFlag(SR.setCarryFlag(1, tools.hexToDecimal(labelWRegisterValue.getText()), ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank())));
        setDigitFlag(SR.setDigitFlag(1, tools.hexToDecimal(labelWRegisterValue.getText()), ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank())));
        
        temp = tools.hexToDecimal(labelWRegisterValue.getText())
            + ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        
        setZeroFlag(SR.setZeroFlag(temp));

        if (temp > 255) {
            temp -= 256;
        }

        saveCommandResult(address, temp);
    }

    private void ANDWF(String address) {
        int temp = tools.hexToDecimal(labelWRegisterValue.getText())
                & ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());

        setZeroFlag(SR.setZeroFlag(temp));

        if (temp > 255) {
            temp -= 256;
        }

        saveCommandResult(address, temp);
    }

    private void CLRF(String address) {
        setfield("00", tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        setZeroFlag(1);
    }

    private void CLRW(String address) {
        labelWRegisterValue.setText("00");
        setZeroFlag(1);
    }

    private void COMF(String address) {
        int selection = tools.binaryToDecimal(address) & 0b10000000;
        int address_ = tools.binaryToDecimal(address) & 0b01111111;
        var temp = ram.getBank(address_, ram.getChosenBank()) ^ 0b11111111;
        if (temp > 255) {
            temp = 1;
        }

        setZeroFlag(SR.setZeroFlag(temp));

        if (selection > 0) {
            setfield(tools.decimalToHex(temp), address_, ram.getChosenBank());
        } else {
            labelWRegisterValue.setText(tools.decimalToHex(temp));
        }
    }

    private void DECF(String address) {
        int temp = ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        temp--;

        if (temp < 0) {
            temp = 256 + temp;
        }

        setZeroFlag(SR.setZeroFlag(temp));
        
        saveCommandResult(address, temp);
    }

    private void DECFSZ(String address) {
        int temp = ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        temp--;
        if (temp == 0) {
            programmCounter.incCounter();
        }
        saveCommandResult(address, temp);
    }

    private void INCF(String address) {
        int temp = ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        temp++;

        if (temp > 255) {
            temp -= 256;
        }

        setZeroFlag(SR.setZeroFlag(temp));

        saveCommandResult(address, temp);
    }

    private void INCFSZ(String address) {
        int temp = ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        temp++;

        if (temp > 255) {
            temp -= 256;
        }

        if (temp == 0) {
            programmCounter.incCounter();
        }
        saveCommandResult(address, temp);
    }

    private void IORWF(String address) {
        int temp = tools.hexToDecimal(labelWRegisterValue.getText())
                | ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());

        setZeroFlag(SR.setZeroFlag(temp));
        saveCommandResult(address, temp);
    }

    private void MOV(String address) {

        if (tools.binaryToDecimal(address) == 8) {
            RETURN();
        } else if (MOVWF(address)) {
            // do nothing
        } else if (tools.binaryToDecimal(address) != 0) {
            if (tools.binaryToDecimal(address) == 100) {
                // reset watchdog
            } else {
                saveCommandResult(address, ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())),
                        ram.getChosenBank()));
            }

        }
    }

    private boolean MOVWF(String address) {
        boolean ret = false;
        if (address.charAt(0) == '1') {
            // int temp = tools.binaryToDecimal(address) & 0b01111111;
            saveCommandResult(address, tools.hexToDecimal(labelWRegisterValue.getText()));
            ret = true;
        }
        return ret;
    }

    private void RLF(String address) {
        int temp = 0;

        temp = ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        int tempCarry = tools.binaryToDecimal(labelCValue.getText());
        setCarryFlag(temp >> 7);
        temp = temp << 1;
        temp = temp & 0b11111111;
        temp = temp | tempCarry;
        saveCommandResult(address, temp);
    }

    private void RRF(String address) {
        int temp = ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        int tempCarry = tools.binaryToDecimal(labelCValue.getText());
        setCarryFlag(temp & 0b00000001);
        temp = temp >> 1;
        temp = temp &0b11111111;
        temp = temp | (tempCarry << 7);
        saveCommandResult(address, temp);
    }

    private void SUBWF(String address) {
        int temp = 0;

        temp = (-1 * tools.hexToDecimal(labelWRegisterValue.getText()))
                + ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());

        setCarryFlag(SR.setCarryFlag(0, ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank()), tools.hexToDecimal(labelWRegisterValue.getText())));
        setDigitFlag(SR.setDigitFlag(0, ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank()), tools.hexToDecimal(labelWRegisterValue.getText())));
        
        if (temp < 0) {
            temp += 256;
        }

        setZeroFlag(SR.setZeroFlag(temp));

        saveCommandResult(address, temp);
    }

    private void SWAPF(String address) {
        int temp = ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());
        String hex = tools.decimalToHex(temp);
        hex = "" + hex.charAt(1) + hex.charAt(0);
        saveCommandResult(address, tools.hexToDecimal(hex));
    }

    private void XORWF(String address) {
        int temp = tools.hexToDecimal(labelWRegisterValue.getText())
                ^ ram.getBank(tools.binaryToDecimal(address.substring(1, address.length())), ram.getChosenBank());

        setZeroFlag(SR.setZeroFlag(temp));
        saveCommandResult(address, temp);
    }

    private void BCF(String fullCode) {
        String bit = fullCode.substring(3, 6);
        String address = fullCode.substring(6, fullCode.length());
        int temp = ram.getBank(tools.binaryToDecimal(address), ram.getChosenBank());
        int bitMask = 0b00000001;
        bitMask = bitMask << tools.binaryToDecimal(bit);

        setfield(tools.decimalToHex((temp ^ bitMask)), tools.binaryToDecimal(address), ram.getChosenBank());
    }

    private void BSF(String fullCode) {
        String bit = fullCode.substring(3, 6);
        String address = fullCode.substring(6, fullCode.length());
        int temp = ram.getBank(tools.binaryToDecimal(address), ram.getChosenBank());
        int bitMask = 0b00000001;
        bitMask = bitMask << tools.binaryToDecimal(bit);

        setfield(tools.decimalToHex((temp | bitMask)), tools.binaryToDecimal(address), ram.getChosenBank());
    }

    private void BTFSC(String fullCode) {
        String bit = fullCode.substring(3, 6);
        String address = fullCode.substring(6, fullCode.length());

        int temp = ram.getBank(tools.binaryToDecimal(address), ram.getChosenBank());
        int bitMask = 0b00000001 << tools.binaryToDecimal(bit);
        temp = temp & bitMask;
        if (temp == 0) {
            programmCounter.incCounter();
        }
    }

    private void BTFSS(String fullCode) {
        String bit = fullCode.substring(3, 6);
        String address = fullCode.substring(6, fullCode.length());

        int temp = ram.getBank(tools.binaryToDecimal(address), ram.getChosenBank());
        int bitMask = 0b00000001 << tools.binaryToDecimal(bit);
        temp = temp & bitMask;
        if (temp > 0) {
            programmCounter.incCounter();
        }
    }

    private void ADDLW(String addressHex) {
        int temp = tools.hexToDecimal(labelWRegisterValue.getText()) + tools.hexToDecimal(addressHex);

        setCarryFlag(SR.setCarryFlag(1, tools.hexToDecimal(labelWRegisterValue.getText()), tools.hexToDecimal(addressHex)));
        setDigitFlag(SR.setDigitFlag(1, tools.hexToDecimal(labelWRegisterValue.getText()), tools.hexToDecimal(addressHex)));
        setZeroFlag(SR.setZeroFlag(temp));

        if (temp > 255) {
            temp -= 256;
        }
        
        labelWRegisterValue.setText(tools.decimalToHex(temp));
    }

    private void ANDLW(String address) {
        labelWRegisterValue.setText(
                tools.decimalToHex(tools.binaryToDecimal(address) & tools.hexToDecimal(labelWRegisterValue.getText())));

        setZeroFlag(SR.setZeroFlag(tools.binaryToDecimal(address) & tools.hexToDecimal(labelWRegisterValue.getText())));
    }

    private void CALL(String fullCode) {
        int i = tools.hexToDecimal(fullCode) & 0b11111111111;

        setStackValue(stack.getPointer(), programmCounter.getCounter());
        stack.increase(programmCounter.getCounter());
        programmCounter.setCounter((i & 0b11111111) - 1);
        if (i > 255) {
            programmCounter.addPCLATHtoCounter();
        }
        //ram.setBank(10, 0, ram.getChosenBank());
    }

    private void CLRWDT() {
        
    }

    private void GOTO(String fullCode) {
        int i = (tools.hexToDecimal(fullCode) & 0b00011111111111);

        programmCounter.setCounter((i & 0b11111111) -1);
        if (i > 255) {
            programmCounter.addPCLATHtoCounter();
        }
        //ram.setBank(10, 0, ram.getChosenBank());
    }

    private void IORLW(String address) {
        labelWRegisterValue.setText(
                tools.decimalToHex(tools.binaryToDecimal(address) | tools.hexToDecimal(labelWRegisterValue.getText())));

        setZeroFlag(SR.setZeroFlag(tools.binaryToDecimal(address) | tools.hexToDecimal(labelWRegisterValue.getText())));
    }

    private void MOVLW(String addressHex) {
        labelWRegisterValue.setText(addressHex);
    }

    private void RETLW(String addressHex) {
        labelWRegisterValue.setText(addressHex);
        setStackValue(stack.getPointer() - 1, 0);
        programmCounter.setCounter(stack.decrease());
    }

    private void RETURN() {
        setStackValue(stack.getPointer() - 1, 0);
        programmCounter.setCounter(stack.decrease());
    }

    private void SUBLW(String addressHex) {
        int num1 = tools.hexToDecimal(addressHex);
        int num2 = tools.hexToDecimal(labelWRegisterValue.getText());
        int temp = num1 - num2;

        setDigitFlag(SR.setDigitFlag(0, num1, num2));
        setCarryFlag(SR.setCarryFlag(0, num1, num2));
        setZeroFlag(SR.setZeroFlag(temp));

        labelWRegisterValue.setText(tools.decimalToHex(temp));
    }

    private void XORLW(String address) {
        int temp = tools.binaryToDecimal(address) ^ tools.hexToDecimal(labelWRegisterValue.getText());
        labelWRegisterValue.setText(
                tools.decimalToHex(temp));
        
        setZeroFlag(SR.setZeroFlag(temp));
    }

    private void setStackValue(int i, int value) {
        switch (i) {
            case 0:
                labelStack0Value.setText(tools.decimalToHex(value));
                break;

            case 1:
                labelStack1Value.setText(tools.decimalToHex(value));
                break;

            case 2:
                labelStack2Value.setText(tools.decimalToHex(value));
                break;

            case 3:
                labelStack3Value.setText(tools.decimalToHex(value));
                break;

            case 4:
                labelStack4Value.setText(tools.decimalToHex(value));
                break;

            case 5:
                labelStack5Value.setText(tools.decimalToHex(value));
                break;

            case 6:
                labelStack6Value.setText(tools.decimalToHex(value));
                break;

            case 7:
                labelStack7Value.setText(tools.decimalToHex(value));
                break;

            default:
                break;
        }
    }

    @FXML
    void checkBox0PortAClick(ActionEvent event) {
        if (checkBoxPin0ValueA.isSelected()) {
            setfield(tools.decimalToHex(0b1 | ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b1111110 & ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
        }
    }

    public void setGieBit(int val) {
        labelGIEValue.setText("1");
        setfield(tools.decimalToHex(ram.getBank(11, 0) & 0b10000000), 11, 0);
    }

    private void setIF(int val) {
        labelIFValue.setText("1");
        setfield(tools.decimalToHex(ram.getBank(11, 0) & 0b00000010), 11, 0);
    }

    @FXML
    void checkBox0PortBClick(ActionEvent event) {

        if (checkBoxPin0ValueB.isSelected()) {
            setfield(tools.decimalToHex(0b1 | ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b11111110 & ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox1PortAClick(ActionEvent event) {
        if (checkBoxPIn1ValueA.isSelected()) {
            setfield(tools.decimalToHex(0b10 | ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b1111101 & ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox1PortBClick(ActionEvent event) {
        if (checkBoxPIn1ValueB.isSelected()) {
            setfield(tools.decimalToHex(0b10 | ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b11111101 & ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox2PortAClick(ActionEvent event) {
        if (checkBoxPIn2ValueA.isSelected()) {
            setfield(tools.decimalToHex(0b00100 | ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b1111011 & ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox2PortBClick(ActionEvent event) {
        if (checkBoxPIn2ValueB.isSelected()) {
            setfield(tools.decimalToHex(0b100 | ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b11111011 & ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox3PortAClick(ActionEvent event) {
        if (checkBoxPin3ValueA.isSelected()) {
        setfield(tools.decimalToHex(0b01000 | ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b1110111 & ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox3PortBClick(ActionEvent event) {
        if (checkBoxPin3ValueB.isSelected()) {
            setfield(tools.decimalToHex(0b1000 | ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b11110111 & ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox4PortAClick(ActionEvent event) {
        int or = ram.getRegisterByAddress(tools.hexToDecimal("81"));
        if (checkBoxPin4ValueA.isSelected()) {
            setfield(tools.decimalToHex(0b10000 | ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
            if ((or & 0b110000) == 0b10000) {
                TimerReturn temp = timer.timerUP(ram.getRegisterByAddress(tools.hexToDecimal("81")),ram.getRegisterByAddress(tools.hexToDecimal("01")), "", ram);
                setTimer(temp.timer);
                buttonStepClick(new ActionEvent());
            }
        }
        else {
            setfield(tools.decimalToHex(0b1101111 & ram.getRegisterByAddress(tools.hexToDecimal("05"))), tools.hexToDecimal("05"), ram.getChosenBank());
            if ((or & 0b110000) == 0b110000) {
                TimerReturn temp = timer.timerUP(ram.getRegisterByAddress(tools.hexToDecimal("81")),ram.getRegisterByAddress(tools.hexToDecimal("01")), "", ram);
                setTimer(temp.timer);
                buttonStepClick(new ActionEvent());
            }
        }
    }

    @FXML
    void checkBox4PortBClick(ActionEvent event) {
        if (checkBoxPin4ValueB.isSelected()) {
            setfield(tools.decimalToHex(0b10000 | ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b11101111 & ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox5PortBClick(ActionEvent event) {
        if (checkBoxPin5ValueB.isSelected()) {
            setfield(tools.decimalToHex(0b100000 | ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b11011111 & ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox6PortBClick(ActionEvent event) {
        if (checkBoxPin6ValueB.isSelected()) {
            setfield(tools.decimalToHex(0b1000000 | ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b10111111 & ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
    }

    @FXML
    void checkBox7PortBClick(ActionEvent event) {
        if (checkBoxPin7ValueA.isSelected()) {
            setfield(tools.decimalToHex(0b10000000 | ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
        else {
            setfield(tools.decimalToHex(0b01111111 & ram.getRegisterByAddress(tools.hexToDecimal("06"))), tools.hexToDecimal("06"), ram.getChosenBank());
        }
    }

    private void updateTris() {
        int trisA = ram.getRegisterByAddress(tools.hexToDecimal("85"));
        int trisB = ram.getRegisterByAddress(tools.hexToDecimal("86"));

        // Tris A
        int trisA0 = trisA & 0b00000001;
        checkBoxTris0ValueA.setSelected(tools.intToBoolean(trisA0));

        int trisA1 = trisA & 0b00000010;
        checkBoxTris1ValueA.setSelected(tools.intToBoolean(trisA1));

        int trisA2 = trisA & 0b00000100;
        checkBoxTris2ValueA.setSelected(tools.intToBoolean(trisA2));

        int trisA3 = trisA & 0b00001000;
        checkBoxTris3ValueA.setSelected(tools.intToBoolean(trisA3));

        int trisA4 = trisA & 0b00010000;
        checkBoxTris4ValueA.setSelected(tools.intToBoolean(trisA4));

        int trisA5 = trisA & 0b00100000;
        checkBoxTris5ValueA.setSelected(tools.intToBoolean(trisA5));

        int trisA6 = trisA & 0b01000000;
        checkBoxTris5ValueA.setSelected(tools.intToBoolean(trisA6));

        int trisA7 = trisA & 0b10000000;
        checkBoxTris5ValueA.setSelected(tools.intToBoolean(trisA7));

        // Tris B
        int trisB0 = trisB & 0b00000001;
        checkBoxTris0ValueA.setSelected(tools.intToBoolean(trisB0));

        int trisB1 = trisB & 0b00000010;
        checkBoxTris1ValueA.setSelected(tools.intToBoolean(trisB1));

        int trisB2 = trisB & 0b00000100;
        checkBoxTris2ValueA.setSelected(tools.intToBoolean(trisB2));

        int trisB3 = trisB & 0b00001000;
        checkBoxTris3ValueA.setSelected(tools.intToBoolean(trisB3));

        int trisB4 = trisB & 0b00010000;
        checkBoxPin4ValueA.setSelected(tools.intToBoolean(trisB4));

        int trisB5 = trisB & 0b00100000;
        checkBoxPin4ValueA.setSelected(tools.intToBoolean(trisB5));

        int trisB6 = trisB & 0b01000000;
        checkBoxPin4ValueA.setSelected(tools.intToBoolean(trisB6));

        int trisB7 = trisB & 0b10000000;
        checkBoxTris7ValueB.setSelected(tools.intToBoolean(trisB7));
    }

    private void updateSFR() {
        int SFRstatus = ram.getRegisterByAddress(tools.hexToDecimal("03"));
        int SFRoption = ram.getRegisterByAddress(tools.hexToDecimal("81"));
        int SFRIntcon = ram.getRegisterByAddress(tools.hexToDecimal("0B"));

        if (ram.getChosenBank() == 0) {
            setfield(tools.decimalToHex(SFRstatus), tools.hexToDecimal("83"), 1);
        }
        else {
            SFRstatus = ram.getRegisterByAddress(tools.hexToDecimal("83"));
            setfield(tools.decimalToHex(SFRstatus), tools.hexToDecimal("03"), 0);
        }
       

        int SFR0 = (SFRstatus & 0b10000000) >> 7;
        labelIRPValue.setText(SFR0 + "");

        int SFR1 = (SFRstatus & 0b01000000) >> 6;
        labelRP1Value.setText(SFR1 + "");

        int SFR2 = (SFRstatus & 0b00100000) >> 5;
        labelRP0Value.setText(SFR2 + "");

        if (SFR2 == 1) {
            ram.setChosenBank(1);
        }
        else {
            ram.setChosenBank(0);
        }

        int SFR3 = (SFRstatus & 0b00010000) >> 4;
        labelToValue.setText(SFR3 + "");

        int SFR4 = (SFRstatus & 0b00001000) >> 3;
        labelPDValue.setText(SFR4 + "");

        int SFR5 = (SFRstatus & 0b00000100) >> 2;
        labelZValue.setText(SFR5 + "");

        int SFR6 = (SFRstatus & 0b00000010) >> 1;
        labelDCValue.setText(SFR6 + "");

        int SFR7 = (SFRstatus & 0b00000001);
        labelCValue.setText(SFR7 + "");

        //OptionRegister
        int SFRopt0 = (SFRoption & 0b10000000) >> 7;
        labelRPuValue.setText(SFRopt0 + "");

        int SFRopt1 = (SFRoption & 0b01000000) >> 6;
        labelIEgValue.setText(SFRopt1 + "");

        int SFRopt2 = (SFRoption & 0b00100000) >> 5;
        labelTCsValue.setText(SFRopt2 + "");

        int SFRopt3 = (SFRoption & 0b00010000) >> 4;
        labelTSeValue.setText(SFRopt3 + "");

        int SFRopt4 = (SFRoption & 0b00001000) >> 3;
        labelPSAValue.setText(SFRopt4 + "");

        int SFRopt5 = (SFRoption & 0b00000100) >> 2;
        labelPS2Value.setText(SFRopt5 + "");

        int SFRopt6 = (SFRoption & 0b00000010) >> 1;
        labelPS1Value.setText(SFRopt6 + "");

        int SFRopt7 = (SFRoption & 0b00000001) >> 0;
        labelPS0Value.setText(SFRopt7 + "");

        //Intcon

        labelGIEValue.setText("" + ((SFRIntcon & 0b10000000) >> 7));
        labelEIE.setText("" + ((SFRIntcon & 0b01000000) >> 6));
        labelTIEValue.setText("" + ((SFRIntcon & 0b00100000) >> 5));
        labelIEValue.setText("" + ((SFRIntcon & 0b00010000) >> 4));
        labelRIEValue.setText("" + ((SFRIntcon & 0b00001000) >> 3));
        labelTIFValue.setText("" + ((SFRIntcon & 0b00000100) >> 2));
        labelIFValue.setText("" + ((SFRIntcon & 0b00000010) >> 1));
        labelRIFValue.setText("" + ((SFRIntcon & 0b00000001) >> 0));
    }

    private void updateSFRW() {
        //ram.setBank(2, programmCounter.getCounter() & 0b11111111, 0);
        setfield(tools.decimalToHex(programmCounter.getCounter() & 0b11111111), 2, 0);

        int PCL = ram.getRegisterByAddress(tools.hexToDecimal("02"));
        int PCLATH = ram.getRegisterByAddress(tools.hexToDecimal("0A"));
        int Status = ram.getRegisterByAddress(tools.hexToDecimal("03"));
        int FSR = ram.getRegisterByAddress(tools.hexToDecimal("04"));
        int Option = ram.getRegisterByAddress(tools.hexToDecimal("81"));
        int Timer0 = ram.getRegisterByAddress(tools.hexToDecimal("01"));

        programmCounter.setPCL(PCL);
        programmCounter.setPCLATH(PCLATH);
        
        labelPCLValue.setText(tools.decimalToHex(PCL));
        labelPCLathValue.setText(tools.decimalToHex(PCLATH));
        labelStatusValue.setText(tools.decimalToHex(Status));
        labelFSRValue.setText(tools.decimalToHex(FSR));
        labelOptionValue.setText(tools.decimalToHex(Option));
        labelTimer0Value.setText(tools.decimalToHex(Timer0));
        labelPCinternValue.setText(tools.decimalToHex(programmCounter.getCounter()));

        //setfield(tools.decimalToHex(ram.getRegisterByAddress(FSR)), tools.hexToDecimal("00"), 1);

    }

    private void reset() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 128; i++) {
                setfield(tools.decimalToHex(0), i, j);
            }
        }
        programmCounter.setCounter(0);
        setCheckBoxByCounter(0);
        lastCommand = 0;
        threadParams = null;
        theBreak = true;
        checkBoxWatchdog.setSelected(false);
        labelWRegisterValue.setText("00");

        for (int i = 0; i < checkBoxDebug.length; i++) {
            checkBoxDebug[i].setText("");
            checkBoxDebug[i].setSelected(false);
        }

        resetPinsAndTris();

        updateAll();
    }

    private void resetPinsAndTris() {
        checkBoxPin0ValueA.setSelected(false);
        checkBoxPIn1ValueA.setSelected(false);
        checkBoxPIn2ValueA.setSelected(false);
        checkBoxPin3ValueA.setSelected(false);
        checkBoxPin4ValueA.setSelected(false);

        checkBoxPin0ValueB.setSelected(false);
        checkBoxPIn1ValueB.setSelected(false);
        checkBoxPIn2ValueB.setSelected(false);
        checkBoxPin3ValueB.setSelected(false);
        checkBoxPin4ValueB.setSelected(false);
        checkBoxPin5ValueB.setSelected(false);
        checkBoxPin6ValueB.setSelected(false);
        checkBoxPin7ValueA.setSelected(false);

        checkBoxTris0ValueA.setSelected(false);
        checkBoxTris1ValueA.setSelected(false);
        checkBoxTris2ValueA.setSelected(false);
        checkBoxTris3ValueA.setSelected(false);
        checkBoxTris4ValueA.setSelected(false);
        checkBoxTris5ValueA.setSelected(false);
        checkBoxTris6ValueA.setSelected(false);
        checkBoxTris7ValueA.setSelected(false);

        checkBoxTris0ValueB.setSelected(false);
        checkBoxTris1ValueB.setSelected(false);
        checkBoxTris2ValueB.setSelected(false);
        checkBoxTris3ValueB.setSelected(false);
        checkBoxTris4ValueB.setSelected(false);
        checkBoxTris5ValueB.setSelected(false);
        checkBoxTris6ValueB.setSelected(false);
        checkBoxTris7ValueB.setSelected(false);

    }

    public void stopWatchdog() {
        watchdog.stop();
        setfield("00", 100, 0);
        labelWatchdog.setText("00");
    }

    public void WatchdogStart() {
        if (checkBoxWatchdog.isSelected()) {

            if (!textFieldQuarz.getText().equals("")) {
                threadParams = new ThreadParams(Integer.parseInt(textFieldQuarz.getText()), ram.getOptionRegister());
            } else {
                threadParams = new ThreadParams(1, ram.getOptionRegister());
            }

            watchdog = new ThreadWatchdog(threadParams);
            watchdog.start();
        }
    }

    private void setZeroFlag(int i) {
        if (i > 0) {
            //zeroFlag = true;
            setfield(tools.decimalToHex(0b00000100 | ram.getRegisterByAddress(03)), 03, 0);
        } else {
            //zeroFlag = false;
            setfield(tools.decimalToHex(0b11111011 & ram.getRegisterByAddress(03)), 03, 0);
        }
    }

    private void setDigitFlag(int i) {
        if (i > 0) {
            //digitFlag = true;
            setfield(tools.decimalToHex(0b00000010 | ram.getRegisterByAddress(03)), 03, 0);
        } else {
            //digitFlag = false;
            setfield(tools.decimalToHex(0b11111101 & ram.getRegisterByAddress(03)), 03, 0);
        }
    }

    private void setCarryFlag(int i) {
        //carryBefore = tools.binaryToDecimal(labelCValue.getText());
        if (i > 0) {
            //carryFlag = true;
            setfield(tools.decimalToHex(0b00000001 | ram.getRegisterByAddress(03)), 03, 0);
        } else {
            //carryFlag = false;
            setfield(tools.decimalToHex(0b11111110 & ram.getRegisterByAddress(03)), 03, 0);
        }
    }

    // private void resetFlags() {
    //     if (zeroFlag) {
    //         setZeroFlag(0);
    //     }

    //     if (digitFlag) {
    //         setDigitFlag(0);
    //     }

    //     if (carryFlag) {
    //         setCarryFlag(0);
    //     }
    // }

    private void incPCounter() {
        programmCounter.incCounter();
        setfield(tools.decimalToHex(programmCounter.getPCL()), 2, 0);
    }

    private void checktTrisReg() {
        int hex85 = ram.getRegisterByAddress(tools.hexToDecimal("85"));
        int hex86 = ram.getRegisterByAddress(tools.hexToDecimal("86"));

        checkBoxTris0ValueA.setSelected((hex85 & 0b1) > 0);
        checkBoxTris1ValueA.setSelected((hex85 & 0b10) > 0);
        checkBoxTris2ValueA.setSelected((hex85 & 0b100) > 0);
        checkBoxTris3ValueA.setSelected((hex85 & 0b1000) > 0);
        checkBoxTris4ValueA.setSelected((hex85 & 0b10000) > 0);
        checkBoxTris5ValueA.setSelected((hex85 & 0b100000) > 0);
        checkBoxTris6ValueA.setSelected((hex85 & 0b1000000) > 0);
        checkBoxTris7ValueA.setSelected((hex85 & 0b10000000) > 0);

        checkBoxTris0ValueB.setSelected((hex86 & 0b1) > 0);
        checkBoxTris1ValueB.setSelected((hex86 & 0b10) > 0);
        checkBoxTris2ValueB.setSelected((hex86 & 0b100) > 0);
        checkBoxTris3ValueB.setSelected((hex86 & 0b1000) > 0);
        checkBoxTris4ValueB.setSelected((hex86 & 0b10000) > 0);
        checkBoxTris5ValueB.setSelected((hex86 & 0b100000) > 0);
        checkBoxTris6ValueB.setSelected((hex86 & 0b1000000) > 0);
        checkBoxTris7ValueB.setSelected((hex86 & 0b10000000) > 0);

        checkBoxPin0ValueA.setDisable((hex85 & 0b1) == 0);
        checkBoxPIn1ValueA.setDisable((hex85 & 0b10) == 0);
        checkBoxPIn2ValueA.setDisable((hex85 & 0b100) == 0);
        checkBoxPin3ValueA.setDisable((hex85 & 0b1000) == 0);
        checkBoxPin4ValueA.setDisable((hex85 & 0b10000) == 0);

        checkBoxPin0ValueB.setDisable((hex86 & 0b1) == 0);
        checkBoxPIn1ValueB.setDisable((hex86 & 0b10) == 0);
        checkBoxPIn2ValueB.setDisable((hex86 & 0b100) == 0);
        checkBoxPin3ValueB.setDisable((hex86 & 0b1000) == 0);
        checkBoxPin4ValueB.setDisable((hex86 & 0b10000) == 0);
        checkBoxPin5ValueB.setDisable((hex86 & 0b100000) == 0);
        checkBoxPin6ValueB.setDisable((hex86 & 0b1000000) == 0);
        checkBoxPin7ValueA.setDisable((hex86 & 0b10000000) == 0);
    }

    private void initializeRam() {
        setfield("18", tools.hexToDecimal("03"), 0);
        
        setfield("FF", tools.hexToDecimal("05"), 1);
        setfield("FF", tools.hexToDecimal("06"), 1);
        
        setfield("FF", tools.hexToDecimal("01"), 1);
        setfield("18", tools.hexToDecimal("03"), 1);
    }

    private void setTimer(int time) {
        field[1].setText("" + time);
        ram.setBank(1, time, 0);
        labelVorteilerValue.setText(tools.decimalToHex(timer.getVorteiler()));
        setZeroFlag(SR.setZeroFlag(time));
    }
}
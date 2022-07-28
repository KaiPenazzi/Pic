package Model;

import converter.tools;

public class RAM {
    Ports p;
    private int chosenBank = 0;
    private static int[] optionRegister= new int[8];
    private int[] Bank0 = new int[128];
    private int[] Bank1 = new int[128];

    public RAM() {
        p = new Ports(Bank0[40], Bank1[40]);
    }


    private void optionRegToRam(){
        int ges = 0;
        ges += optionRegister[0] * 1;
        ges += optionRegister[1] * 2;
        ges += optionRegister[2] * 2*2;
        ges += optionRegister[3] * 2*2*2;
        ges += optionRegister[4] * 2*2*2*2;
        ges += optionRegister[5] * 2*2*2*2*2;
        ges += optionRegister[6] * 2*2*2*2*2*2;
        ges += optionRegister[7] * 2*2*2*2*2*2*2;

        Bank1[tools.hexToDecimal("81") - 128] = ges;
    }

    private void ramToOptionregister() {
        int reg = Bank1[tools.hexToDecimal("81") - 128];
        optionRegister[0] = reg & 0b00000001;
        optionRegister[1] = reg & 0b00000010;
        optionRegister[2] = reg & 0b00000100;
        optionRegister[3] = reg & 0b00001000;
        optionRegister[4] = reg & 0b00010000;
        optionRegister[5] = reg & 0b00100000;
        optionRegister[6] = reg & 0b01000000;
        optionRegister[7] = reg & 0b10000000;
    }

    public int[] getOptionRegister() {
        return optionRegister;
    }

    public void setOptionRegister(int[] var){
        optionRegister = var;
        optionRegToRam();
    }

    public void setBank(int pos, int in, int bank){
        if (bank == 0) {
            Bank0[pos] = in;
        }
        else{
            Bank1[pos] = in;
        }
        ramToOptionregister();
    }    
    public int getBank(int pos, int bank){
        if (bank == 0) {
            return Bank0[pos];
        }
        else{
            return Bank1[pos];
        }
    }

    public int[] getFullBank0(){
        return Bank0;
    }

    public int[] getFullBank1(){
        return Bank1;
    }

    // public void setRegisterByAddress(int address, int value){
    //     if (address < 128){
    //         Bank0[address] = value;
    //     }
    //     else {
    //         Bank1[address - 128] = value;
    //     }
    // }

    public int getRegisterByAddress(int address){
        int ret;

        if (address < 127){
            ret = Bank0[address];
        }
        else{
            ret = Bank1[address-128];
        }

        return ret;
    }

    public void setChosenBank(int value){
        if (value < 1){
            chosenBank = 0;
        }
        else{
            chosenBank = 1;
        }
    }

    public int getChosenBank(){
        return chosenBank;
    }
}


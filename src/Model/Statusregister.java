package Model;

import converter.tools;

public class Statusregister {
    // 0 oder 1 möglich, greift auf entsprechend Bank0 oder Bank1 zu
    private int RP0Bit = 0;
    private int DigitCarryFlag;
    // carryflag wird 1 wenn durch addition die obergrenze überschritten wird
    private int CarryFlag;
    // ergebniss einer operation == Null-> zeroflag = 1; else Null
    private int ZeroFlag;

    public int getRP0Bit() {
        return RP0Bit;
    }

    public int setZeroFlag(int counter) {
        if (counter == 0) {
            ZeroFlag = 1;
        } else {
            ZeroFlag = 0;
        }
        return ZeroFlag;
    }

    public int setCarryFlag(int status, int valOne, int valTwo) {
        // 0 = subtraktion
        // 1 = addition

        if (status == 0) {
            if (valTwo == 0) {
                CarryFlag = 1;
                return CarryFlag;
            }
            if (valOne - valTwo >= 0) {
                CarryFlag = 1;
            } else {
                CarryFlag = 0;
            }
        } else {
            if (valTwo == 0) {
                CarryFlag = 0;
                return CarryFlag;
            }
            if (valOne + valTwo > 255) {
                CarryFlag = 1;
            } else {
                CarryFlag = 0;
            }
        }
        return CarryFlag;
    }

    public int setDigitFlag(int status, int valOne, int valTwo) {
        // 0 = subtraktion
        // 1 = addition

        if (valTwo == 0 && status == 0) {
            DigitCarryFlag = 1;
            return DigitCarryFlag;
        } else if (valTwo == 0 && status == 1) {
            DigitCarryFlag = 0;
            return DigitCarryFlag;
        }

        if (status == 0) {
            valTwo += 1;
        }
        
        String one = tools.decimalToHex(valOne);
        String two = tools.decimalToHex(valTwo);
        
        one = tools.hexToBinary(one);
        two = tools.hexToBinary(two);

        String strSol = tools.binaryAdd(one, two);
        if (strSol.length() >= 4) {
            
            if (strSol.charAt(3) == '1') {
                DigitCarryFlag = 1;
            } 
            else{
                DigitCarryFlag = 0;
            }
            return DigitCarryFlag;
        }
        return 0;
    }

}

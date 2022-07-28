package Model;

import converter.tools;

public class Timer {
    private int vorteiler = 1;

    public int getVorteiler(){
        return vorteiler;
    }

    public void resetVorteiler() {
        vorteiler = 1;
    }

    public TimerReturn timerUP(int optionRegister, int timer0, String Befehl, RAM ram) {
        boolean overflow = false;
        vorteiler+= getZyklen(Befehl);
        if (vorteiler > getZyklus(optionRegister)) {
            vorteiler = vorteiler % getZyklus(optionRegister);
            timer0++;
        }
        if (timer0 > 255){
            timer0 = 0;
            overflow = true;
            ram.setBank(tools.hexToDecimal("0B"), ram.getBank(tools.hexToDecimal("0B"), ram.getChosenBank()) | 0b100, ram.getChosenBank());
        }

        return new TimerReturn(timer0, overflow);
    }

    public int timerUPE(int optionRegister, int timer0) {
        vorteiler+= 1;
        if (vorteiler > getZyklus(optionRegister)) {
            vorteiler = vorteiler % getZyklus(optionRegister);
            timer0++;
        }
        if (timer0 > 255){
            timer0 = 0;
        }
    
        return timer0;
    }

    private int getZyklus(int OR) {
        int temp = OR & 0b00000111;
        int ret = 0;
        if ((OR & 00001000) == 0) {
            switch (temp) {
                case 0:
                    ret = 2;
                    break;

                case 1:
                    ret = 4;
                    break;

                case 2:
                    ret = 8;
                    break;

                case 3:
                    ret = 16;
                    break;

                case 4:
                    ret = 32;
                    break;

                case 5:
                    ret = 64;
                    break;

                case 6:
                    ret = 128;

                case 7:
                    ret = 256;

                default:
                    break;
            }
        }

        return ret;
    }

    private int getZyklen(String command) {
        int iRet = 1;
        if (command.length() > 0) {
            switch (command) {
                default:
                    switch (command.charAt(0)) {
                        case '2':
                            iRet = 2;
                            break;
                    }
                    break;
            }
        }
        return iRet;
    }
}

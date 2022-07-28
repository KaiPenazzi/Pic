package Model;

public class ThreadTimer extends Thread{
    ThreadParams threadParams;
    private int vorteiler;
    private int PSABit = 0;

    public ThreadTimer(ThreadParams input, int timer0Reg) {
        threadParams = input;
    }

    @Override
    public void run() {
        
    }

    public int getVorteilerStatus(int[] optionRegister) {
        for (int i = 0; i < optionRegister.length; i++) {
            if (optionRegister[i] == 1) {
                PSABit = 1;
            } else {
                // vorteiler nicht Watchdog zugewiesen, sondern timer
                PSABit = 0;
            }
        }
        return PSABit;
    }

    public int getTeilerFaktor() {
        int vorteilerFak = getVorteiler();
        int teilerFaktor = 0;
        if (getVorteilerStatus(threadParams.optionRegister) == 1) {
            teilerFaktor = 1;
        } else if (getVorteilerStatus(threadParams.optionRegister) == 0) {
            teilerFaktor = 2;
        }
        for (int i = 0; i < vorteilerFak; i++) {
            teilerFaktor = teilerFaktor * 2;
        }
        return teilerFaktor;
    }

    public int getVorteiler() {
        vorteiler++;
        if (vorteiler >= 8) {
            vorteiler = 0;
        }
        return vorteiler;
    }
}

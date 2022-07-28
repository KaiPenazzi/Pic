package Model;

public class ThreadWatchdog extends Thread {
    ThreadParams threadParams;
    private int PSABit = 0;
    private double watchDogVariable = 0.018;
    private int vorteiler;

    public ThreadWatchdog(ThreadParams input){
        super();
        threadParams = input;
    }

    @Override
    public void run() {
        startWatchdog(threadParams.quarztakt);
        threadParams.ret = true;
    }

    public boolean startWatchdog(double quarztakt) {
        boolean resetPic = false;
        double watchDog = watchDogVariable;
        if (getVorteilerStatus(threadParams.optionRegister) == 1) {
            for (int i = 0; i < threadParams.optionRegister.length + 10; i++) {
                watchDog += watchDog;
                if (watchDog <= (0.018 * getTeilerFaktor())) {
                    resetPic = false;
                } else {
                    resetPic = true;
                    return resetPic;
                }

                try {
                    sleep((long)quarztakt);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        } else if (getVorteilerStatus(threadParams.optionRegister) == 0) {

        }
        return resetPic;
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

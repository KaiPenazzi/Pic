package Model;

public class Programmspeicher {
    // private int[] ProgSpeicher = new int[1024];

    public int progzaehler() {
        int programmZaehler = 0;
        programmZaehler += 1;
        if (programmZaehler > 1023) {
            programmZaehler = 0;
        }
        return programmZaehler;
    }
}

package Model;

public class ThreadParams {
    public int quarztakt;
    public int[] optionRegister;
    public boolean ret;

    public ThreadParams(int q, int[] or) {
        quarztakt = q;
        optionRegister = or;
        ret = false;
    }
}

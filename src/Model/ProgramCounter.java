package Model;

public class ProgramCounter {
    private int counter;
    private int pcl;
    private int pclath;

    public int getCounter() {
        return counter;
    }

    public void addPCLATHtoCounter() {
        counter = counter | (getPCLATH()  << 8);
    }

    public void setCounter(int Counter) {
        if (Counter < 0) {
            Counter = 0;
        }
        counter = Counter;
    }

    public int getPCL() {
        return pcl;
    }

    public void setPCL(int PCL) {
        pcl = PCL;
        counter = (counter & 0b111100000000) | PCL;
    }

    public int getPCLATH() {
        return pclath;
    }

    public void setPCLATH(int PCLATH) {
        pclath = PCLATH;
    }

    public ProgramCounter() {
        counter = 0;
        pcl = 0;
        pclath = 0;
    }

    public void incCounter() {
        counter++;
        pcl++;
        if (pcl > 255) {
            pcl = 0;
        }
    }

}
package Model;

import Model.Enum.status;
import converter.tools;

public class Ports {

    public status IOPinDetermination(int trisLatch, int dataLatch){
        status IOPin = status.AUSGANG;
        status[] eStatus = getStatus(trisLatch, dataLatch);
            if (eStatus[0] == status.GESPERRT && eStatus[1] == status.GESPERRT) {
                IOPin = status.EINGANG;
            }
        return IOPin;
    }

    public boolean IsInterrupt(int trisLatch, int dataLatch){
        if (IOPinDetermination(trisLatch, dataLatch) == status.EINGANG) {
            //interrupt wird ausgelöst bei RB4, 5, 6, 7
           
            return true;
        }
        return false;
    }

    public int PTransistorDeterm(int trisLatch, int dataLatch) {
        int pTransistor = 0;
            
        if (dataLatch == 1) {
                dataLatch = 0;
        }
        else{
                dataLatch = 1;
        }
        
        pTransistor = tools.ORGatter(trisLatch, dataLatch);
        return pTransistor;
    }
    public int NTransistorDeterm(int trisLatch, int dataLatch) {
        int nTransistor = 0;
            if (dataLatch == 1) {
                dataLatch = 0;
            }
            else{
                dataLatch = 1;
            }
            if (trisLatch == 1) {
                trisLatch = 0;
            }
            else{
                trisLatch = 1;
            }

            nTransistor = tools.ANDGatter(trisLatch, dataLatch);
            return nTransistor;
    }

    public status[] getStatus(int trisLatch, int dataLatch){
        status[] eStatus = {status.GESPERRT, status.GESPERRT};
        if (NTransistorDeterm(trisLatch, dataLatch) == 1) {
            eStatus[0] = status.OFFEN;
        }
        if(PTransistorDeterm(trisLatch, dataLatch) == 0){
            eStatus[1] = status.OFFEN;
        }
        return eStatus;
    }

    int trisLatch;
    int dataLatch;
    public Ports(int trisLatch, int dataLatch){
       this.trisLatch = trisLatch;
       this.dataLatch = dataLatch;
    }
    
}

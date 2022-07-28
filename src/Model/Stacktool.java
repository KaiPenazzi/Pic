package Model;


public class Stacktool {
    //speicherung der Rückkehradresse
    int pointer = 0;
    int[] stackArr = new int[8];
    public void increase(int rueckkehrardresse) {    
        //CALL
        stackArr[pointer] = rueckkehrardresse;
        pointer++;
        if (pointer >= 8) {
            pointer = 0;
        }
    } 
    public int decrease() {       
    //RETURN, RETLW, RETFIE
        pointer--;
        if(pointer < 0){
            pointer = 7;
        }
        return stackArr[pointer];
    }

    public int getPointer(){
        return pointer;
    }

    public int getStackIndexValue(int i){
        return stackArr[i];
    }
}

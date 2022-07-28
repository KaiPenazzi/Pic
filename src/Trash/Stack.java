package Trash;

public class Stack {
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
public int decrease(int rueckkehrardresse) {       
   //RETURN, RETLW, RETFIE
    pointer--;
    if(pointer < 0){
        pointer = 7;
    }
    return stackArr[pointer];
}
}

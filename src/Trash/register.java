package Trash;

public abstract class register {
    bit[] valueRegister;

    register(){
        valueRegister = new bit[8];
    }

    public abstract bit[] getValueRegister();
    public abstract void setValueRegister(bit[] index);


    public abstract int getDecimal();
    public abstract void setDecimal(int index);

    /**
     * 
     * @return String of two chars
     */
    public abstract String getHex();
    public abstract void setHex(String index);

}

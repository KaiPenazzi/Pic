package converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.border.StrokeBorder;

//import javax.lang.model.util.ElementScanner14;

public class tools {

    public static ArrayList<String> readFile(String Path) {
        ArrayList<String> list = new ArrayList<>();
        File file = new File(Path);
        BufferedReader br = null;
        String command;

        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                command = line.substring(5, 9);
                if (!command.equals("    ")) {
                    list.add(command);
                }
            }
        } catch (Exception e) {
            System.out.println("Die Datei konnte nicht ausgeslen werden: " + e);
        }

        return list;
    }

    public static String hexToBinary(String hex) {
        String binary = Integer.toBinaryString(hexToDecimal(hex));
        while(binary.length() < 8){
            binary = "0" + binary;
        }
        return binary;
    }

    public static int binaryToDecimal(String binary){
        return Integer.parseInt(binary, 2);
    }

    public static int hexToDecimal(String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static String decimalToHex(int decimal){
        String ret;
        if (decimal < 16){
            ret = "0" + Integer.toHexString(decimal);
        }
        else{
            ret = Integer.toHexString(decimal);
        }
        return ret;
    }

    public static int ANDGatter(int input1, int input2) {
        int ret;
        if (input1 == 0 || input2 == 0) {
            ret = 0;
        }
        else{
            ret = 1;
        }
        
        return ret;
    }

    public static int ORGatter(int input1, int input2) {
        int ret;
        if (input1 == 0 && input2 == 0) {
            ret = 0;
        }
        else{
             ret = 1;
        }
        return ret;
    }

    public static String getFullFile(String Path) {
        String fullFile = "";
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(Path));
            while ((line = br.readLine()) != null) {
                fullFile += line + "\n";
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return fullFile;
    }

    public static String binaryAdd(String strOne, String strTwo){
        String strSol = "";
        String iUebertrag = "0";
        int length = strTwo.length();
        if (strOne.length() < strTwo.length()) {
            length = strOne.length();
        }
        for (int i = length - 1; i >= 0; i--) {
            if (strOne.charAt(i) == '1' && strTwo.charAt(i) == '1' && iUebertrag == "0") {
                iUebertrag = "1";
                strSol += iUebertrag;
            }
            else if(strOne.charAt(i) == '1' && strTwo.charAt(i) == '1' && iUebertrag == "1"){
                strSol += iUebertrag;
            }
            else if((strOne.charAt(i) == '0' && strTwo.charAt(i) == '1' && iUebertrag == "0") || (strOne.charAt(i) == '1' && strTwo.charAt(i) == '0' && iUebertrag == "0")){
                strSol += iUebertrag;
            }
            else if(strOne.charAt(i) == '0' && strTwo.charAt(i) == '0' && iUebertrag == "0"){
                strSol += iUebertrag;
            }
            else if(strOne.charAt(i) == '0' && strTwo.charAt(i) == '0' && iUebertrag == "1"){
                iUebertrag = "0";
                strSol += iUebertrag;
            }
            else if(strOne.charAt(i) == '1' && strTwo.charAt(i) == '0' && iUebertrag == "1" || strOne.charAt(i) == '0' && strTwo.charAt(i) == '1' && iUebertrag == "1"){
                iUebertrag = "1";
                strSol +=iUebertrag;
            }
        }
        return strSol;
    }

    public static boolean intToBoolean(int input){
        if (input > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public static int countLine(String Path) {
        int lines = 0;
        
        try {
            File file = new File(Path);
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            lines = (int) br.lines().count();

            // while (br.readLine() != null) {
            //    lines++;
            // }
  
        } catch (Exception e) {
            e.printStackTrace();
        }
  
        return lines;
    }


}

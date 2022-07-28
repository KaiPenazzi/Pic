// package Model;

// public class Watchdog {
//     // private int[] watchDogStatus = new int[2];
//     private double watchDog = 0.018;
//     private int vorteiler = 0;
//     private int timer = 0;
//     private int T0IFBit = 0;
//     private int T0CSBit = 0;
//     private int PSABit = 0;

//     // Timer0
//     public void setTimer(int val) {
//         timer = val;
//     }

//     public void setT0IFBit() {
//         if (timer >= 128) {
//             T0IFBit = 1;
//             timer = 0;
//         }
//         timer++;
//     }

//     public void setT0CSBit(int val) {
//         if (val == 0 || val == 1) {
//             T0CSBit = val;
//         }
//     }

//     public int getT0CSBit() {
//         return T0CSBit;
//     }

//     public void setPSABit(int val) {
//         PSABit = val;
//     }

//     public int getPSABit() {
//         return PSABit;
//     }

//     public void decision() {
//         if (getT0CSBit() == 0) {
//             // Befehlstakt zum zählen
//         } else {
//             multiplexer();
//         }
//     }

//     private void multiplexer() {
//         if (getVorteiler() == 0) {
//             setPSABit(1);
//         } else {
//             setVorteiler();
//         }
//     }

//     // Watchdog
//     public void setWatchdog(double watchDog) {
//         // wenn CLRWDT, dann wird er zurückgesetzt(18ms ist kleinste zeit)
//         watchDog = 0.018;
//     }

//     public double getWatchDog() {
//         return watchDog;
//     }

//     public int[] getOptionRegister() {
//         return RAM.getOptionRegister();
//     }

//     // wenn pic resettet wird
//     public void setOptionRegister(int[] optionRegister) {
//         for (int i = 0; i < RAM.getOptionRegister().length; i++) {
//             optionRegister[i] = 1;
//         }
//     }

//     public int getVorteilerStatus(int[] optionRegister) {
//         for (int i = 0; i < optionRegister.length; i++) {
//             if (optionRegister[i] == 1) {
//                 setPSABit(1);
//             } else {
//                 // vorteiler nicht Watchdog zugewiesen, sondern timer
//                 setPSABit(0);
//             }
//         }
//         return getPSABit();
//     }

//     public int getVorteiler() {
//         vorteiler++;
//         if (vorteiler >= 8) {
//             vorteiler = 0;
//         }
//         return vorteiler;
//     }

//     public void setVorteiler() {
//         vorteiler = 0;
//     }

//     public int getTeilerFaktor() {
//         int vorteilerFak = getVorteiler();
//         int teilerFaktor = 0;
//         if (getVorteilerStatus(getOptionRegister()) == 1) {
//             teilerFaktor = 1;
//         } else if (getVorteilerStatus(getOptionRegister()) == 0) {
//             teilerFaktor = 2;
//         }
//         for (int i = 0; i < vorteilerFak; i++) {
//             teilerFaktor = teilerFaktor * 2;
//         }
//         return teilerFaktor;
//     }

//     public boolean startWatchdog(int quarztakt) {
//         boolean resetPic = false;
//         double watchDog = getWatchDog();
//         if (getVorteilerStatus(getOptionRegister()) == 1) {
//             for (int i = 0; i < getOptionRegister().length + 10; i++) {
//                 watchDog += watchDog;
//                 if (watchDog <= (0.018 * getTeilerFaktor())) {
//                     setWatchdog(watchDog);
//                     resetPic = false;
//                 } else {
//                     resetPic = true;
//                     setOptionRegister(RAM.getOptionRegister());
//                     return resetPic;
//                 }
//             }
//         } else if (getVorteilerStatus(getOptionRegister()) == 0) {

//         }
//         return resetPic;
//     }

//     // takt
//     public double getQuarzTakt(double quarztakt) {
//         return quarztakt;
//     }

//     public double getBefehlsZyklus(double quarztakt) {
//         double befehlsZyklus = getQuarzTakt(quarztakt) / 4;
//         return befehlsZyklus;
//     }
// }

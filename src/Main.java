import java.util.Scanner;

public class Main {
    public static Scanner INPUT = new Scanner(System.in);
    public static int SHIFT = 2;
    public static int GROUPNUM = 2;

    public static void main(String[] args){
        menu();
    }

    public static void menu(){

        print("Type 'encrypt' or 'decrypt' to begin. Type 'exit' to close the program.");
        String choice = INPUT.nextLine().toLowerCase().trim();

        if (choice.equals("encrypt")){
            print("Input message to encrypt.");
            String text = INPUT.nextLine();
            print(encryptString(text, SHIFT, GROUPNUM));
            menu();
        }
        else if (choice.equals("decrypt")){
            print("Input message to decrypt.");
            String text = INPUT.nextLine();
            print(decryptString(text, SHIFT));
            menu();
        }else if (choice.equals("exit")){
            System.exit(0);
        }else{
            menu();
        }
    }

    public static String normalizeText(String str){

        String removeChars = ".,:; '\"!?()@#$%^&*-_=+|\\<>`~0987654321[]{}";

        String normalizedText = "";

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int idx = removeChars.indexOf(c);
            if(idx == -1){
                normalizedText += c;
            }
        }

        normalizedText = normalizedText.toUpperCase();

        return normalizedText;
    }

    public static String obify(String str){

        String vowels = "AEIOUY";
        String obifiedText = "";

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int idx = vowels.indexOf(c);
            if (idx > -1) {
                obifiedText += "OB";
            }
                obifiedText += c;

        }

        return obifiedText;
    }

    public static String ceasarify(String str, int shift){

        String ceasarifiedText = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = shiftAlphabet(shift);

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int idx = alphabet.indexOf(c);
            ceasarifiedText += shiftedAlphabet.charAt(idx);
        }

        return ceasarifiedText;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String groupify(String str, int num){

        String groupifiedText = "";

        while (str.length() % num != 0){
            str += "x";
        }

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(i != 0 && i % num == 0){
                groupifiedText += " ";
            }
            groupifiedText += c;
        }

        return groupifiedText;
    }

    public static void print(String str){
        System.out.println(str);
    }

    public static String ungroupify(String str) {

        String ungroupifiedText = "";

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != 'x') {
                ungroupifiedText += c;
            }

        }

        return ungroupifiedText;
    }

    public static String decaesarify(String str, int shift){

        String deceasarifiedText = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = shiftAlphabet(shift);

        str = str.toUpperCase();

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            int idx = shiftedAlphabet.indexOf(c);
            deceasarifiedText += alphabet.charAt(idx);
        }

        return deceasarifiedText;
    }

    public static String deobify(String str){

        String[] obbs = {"OBA", "OBE", "OBI", "OBO", "OBU", "OBY"};
        String[] vowels = {"A", "E","I","O","U","Y"};
        String deobifiedText = str;

        for (int i = 0; i < obbs.length; i++){
            deobifiedText = deobifiedText.replaceAll(obbs[i], vowels[i]);
        }

        return deobifiedText;
    }

    public static String encryptString(String text, int shift, int num){
        String normalizedText = normalizeText(text);
        String obifiedText = obify(normalizedText);
        String ceasarifiedText = ceasarify(obifiedText, shift);
        String groupifiedText = groupify(ceasarifiedText, num);
        return groupifiedText;
    }

    public static String decryptString(String text, int shift){

        String ungroupifiedText = ungroupify(text);
        String deceasarifiedText = decaesarify(ungroupifiedText, shift);
        String deobifiedText = deobify(deceasarifiedText);
        return deobifiedText;
    }

}

/*
  This program is designed to complete several tasks surrounding Morse Code Translation.
  Morse Code is a simple binary language using dots “ . “ and dashes “-”. This program uses a “Menu”
  to allow users to select one of 5 options; Translate English to Morse code, Translate Morse code
  to English, Print a conversion chart, Quit the program, and finally Reload menu. The while loop
  in the main method will run the program until the user decides to close the program.
*/
import java.util.Scanner;
public class MorseCodeTranslator {

    static String[][] morseLookUp = {{"A",".-"},{"B","-..."},{"C","-.-."},{"D","-.."},{"E","."},{"F","..-."},{"G","--."},
            {"H","...."},{"I",".."},{"J",".---"},{"K","-.-"},{"L",".-.."},{"M","--"},{"N","-."},{"O","---"},
            {"P",".--."},{"Q","--.-"},{"R",".-."},{"S","..."},{"T","-"},{"U","..-"},{"V","...-"},{"W",".--"}
            ,{"X","-..-"},{"Y","-.--"},{"Z","--.."},{"1",".----"},{"2","..---"},{"3","...--"},{"4","....-"}
            ,{"5","....."},{"6","-...."},{"7","--..."},{"8","---.."},{"9","----."},{"0","-----"}};

    static Scanner userInput = new Scanner(System.in);
    static int programRunning = 0;
    static int firstMenu = 0;
    static String message;
    static String userOption;
    static String letter ="";
    static int optionNumber;
    static int menuNumber;

    // displays a menu for the user to select from
    public static int mainMenu(){
        // the welcome message will only run once.
        if (firstMenu==0){
            System.out.println("\nWelcome to the Morse Code Translator!");
            firstMenu++;
        }
        System.out.println("\n\nSelect an option by entering the number next to it. ");
        System.out.println("\t1 - English to Morse Code");
        System.out.println("\t2 - Morse Code to English");
        System.out.println("\t3 - Print conversion chart");
        System.out.println("\t4 - Quit program");
        System.out.println("\t5 - Reload menu");
        String selection = userInput.nextLine();
        try{
            menuNumber = Integer.parseInt(selection);
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error. Please try again");
            mainMenu();
        }
        return menuNumber;
    }
   /* Takes the option chosen by the user in the mainMenu method to call other methods or to stop the main while
      loop*/
    public static void selection(int optionNumber){

        switch (optionNumber) {
            case 1:
                engToMorse();
                break;
            case 2:
                morseToEng();
                break;
            case 3:
                printChart(morseLookUp);
                break;
            case 4:
                programRunning = optionNumber;
                break;
            case 5:
                programRunning = 0;
        }
        if (optionNumber<=0 || optionNumber>5){
            System.out.println("Invalid option. Try again.");
        }
    }

    /* Get the message in english from the user, so it can be sent to the getChar method.*/
    public static void engToMorse(){

        System.out.println("Enter a message in English to generate Morse Code: ");
        message = userInput.nextLine();
        getChar(message);
        userOptionSelection(1);
    }

    /* Get the message in Morse Code from the user, so it can be sent to the parsMorseCode method.*/
    public static void morseToEng(){
        //System.out.println("this is the morse to english method");
        System.out.println("Enter a message in Morse to generate English, place a space between every letter/number ");
        message = userInput.nextLine();
        parseMorseCode(message+ " ");
        userOptionSelection(2);
    }

    /*Iterates though the Morse Code message looking for spaces to denote a complete character.
      The individual dots and dashes are stored in "letter" and then passed to the
      "translate" method */
    public static void parseMorseCode(String morseCode){

        for(int i=0; i<morseCode.length();i++) {
            char valueChar = morseCode.charAt(i);
            //Char type is cast to String type
            String valueString = String.valueOf(valueChar);
            if (!valueString.equals(" ")) {
                letter += valueString;
            }
            else {
                translate(morseLookUp, letter, 1, 0);
                letter = "";
            }
        }
        letter ="";
    }

    /* This method prints the morseLookUp array*/
    public static void printChart(String[][]morseArray){
        System.out.println("Morse code translation chart");
        for (int i=0;i<morseArray.length;i++) {
            System.out.println(morseArray[i][0]+ " "+morseArray[i][1]);
        }
        userOptionSelection(3);
    }

    /*Once the translation is done the user is prompted to repeat the current action, return to the
    * menu or to quit the program*/
    public static void userOptionSelection(int menuNumber){

        System.out.println("\nTo repeat enter " +menuNumber+", to Quit enter 4, or enter 5 to return to the menu.");
        userOption = userInput.nextLine();
        try {
            optionNumber = Integer.parseInt(userOption);
        }
        catch(NumberFormatException nfe){
            System.out.println("Only Numbers are accepted. Please tyr again.");
            userOptionSelection(menuNumber);
        }
        if (optionNumber==menuNumber||optionNumber==4||optionNumber==5) {
            selection(optionNumber);
        }else{
            System.out.println("Invalid choice. Try again");
            userOptionSelection(menuNumber);
        }
    }
    /*This method takes in the user message and converts the text into individual String characters.
      The method then calls the "translate" method to convert the message to Morse code.
     */
    public static void getChar (String text){
        String upperCaseText = text.toUpperCase();
        int length = upperCaseText.length();
        for(int i=0; i<length;i++){
            char valueChar = upperCaseText.charAt(i);
            //Char type is cast to String type
            String valueString =String.valueOf(valueChar);
            translate(morseLookUp,valueString,0,1);
        }
    }

    /*The "translate" method takes either a single english character or a Morse code "character". The for loop
    * searches for the correct index in the 2d array and then returns the proper translation. */
    public static void translate(String[][]morseArray,String stringText,int index1,int index2){
       /* the boolean is set true of the value is found in the array. if not true the character
        is not in the array*/
        boolean charFound = false;
        for(int i=0;i<morseArray.length;i++) {

            if (stringText.equals(" ")) {
                System.out.print("/");
                charFound = true;
                break;
            }else if (stringText.equals(morseArray[i][index1])) {
                System.out.print(morseArray[i][index2] +" ");
               // System.out.println("translate else if");
                charFound = true;
                break;
            }
        }
        // if the character is not in the array this prints an invalid statement.
        if (!charFound){
            System.out.println("\n"+stringText+" Is invalid");
        }
    }

    /*The main method runs the while loop till the user chooses to quit the program. Once the
    * program has "quit" the main method displays a goodbye message*/
    public static void main(String[] args) {

        while (programRunning!=4){
           selection(mainMenu());
        }
        System.out.println("Thanks for using the Morse Code Translator!");
    }
}
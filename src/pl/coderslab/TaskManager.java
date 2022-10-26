package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

private static final String[] OPTIONS_TO_CHOOSE = {"add", "remove", "list", "exit"};

    public static void main(String[] args) {

        String[][] tasks = loadFile("tasks.csv");

        showOptions();

        Scanner scan = new Scanner(System.in);

        String choosenOption = scan.nextLine();

        while (!choosenOption.equals("exit")) {

            switch (choosenOption) {
                case "add":
                    System.out.println("ADD: tutaj ma się coś zadziać");
                    break;
                case "remove":
                    System.out.println("REMOVE: tutaj ma się coś zadziać");
                    break;
                case "list":
                    System.out.println("LIST: tutaj ma się coś zadziać");
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Wrong input. " + ConsoleColors.RESET + "Choose one of the option listed below.");
            }



            showOptions();
            choosenOption = scan.nextLine();
        }

        System.out.println(ConsoleColors.RED + "Bye, bye." + ConsoleColors.RESET);



    }


    public static void showOptions() {

        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);

        for (int i = 0; i < OPTIONS_TO_CHOOSE.length; i++) {
            System.out.println(OPTIONS_TO_CHOOSE[i]);
        }

    }


    public static String[][] loadFile(String filename) {

        File file = new File(filename);
        String line;
        String[][] arrLines = new String[0][3];

        try (Scanner scanFromFile = new Scanner(file)){
            while (scanFromFile.hasNextLine()) {
                line = scanFromFile.nextLine();

                arrLines = Arrays.copyOf(arrLines, arrLines.length +1);
                arrLines[arrLines.length-1] = line.split(",");

                //scanFromFile.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return arrLines;

    }


}

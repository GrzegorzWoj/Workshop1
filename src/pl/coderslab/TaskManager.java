package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManager {

    private static final String[] OPTIONS_TO_CHOOSE = {"add", "remove", "list", "exit"};

    public static void main(String[] args) {

        String[][] tasks = loadFromFile("tasks.csv");

        showOptions();

        Scanner scan = new Scanner(System.in);

        String choosenOption = scan.nextLine();

        while (!choosenOption.equals("exit")) {

            switch (choosenOption) {
                case "add":
                    tasks = add(tasks);
                    break;
                case "remove":
                    tasks = remove(tasks);
                    break;
                case "list":
                    list(tasks);
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Wrong input. " + ConsoleColors.RESET + "Choose one of the option listed below.");
            }


            showOptions();
            choosenOption = scan.nextLine();
        }

        saveToFile(tasks, "tasks.csv");
        System.out.println(ConsoleColors.RED + "Bye, bye." + ConsoleColors.RESET);

    }


    public static void showOptions() {

        System.out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);

        for (int i = 0; i < OPTIONS_TO_CHOOSE.length; i++) {
            System.out.println(OPTIONS_TO_CHOOSE[i]);
        }

    }


    public static String[][] loadFromFile(String filename) {

        File file = new File(filename);
        String line;
        String[][] arrLines = new String[0][3];

        try (Scanner scanFromFile = new Scanner(file)) {
            while (scanFromFile.hasNextLine()) {
                line = scanFromFile.nextLine();

                arrLines = Arrays.copyOf(arrLines, arrLines.length + 1);
                arrLines[arrLines.length - 1] = line.split(",");

                //scanFromFile.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return arrLines;

    }


    public static void list(String[][] arrayToListed) {

        for (int i = 0; i < arrayToListed.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < arrayToListed[i].length; j++) {
                System.out.print(arrayToListed[i][j] + "\t");
            }
            System.out.println();
        }

    }


    public static String[][] add(String[][] arrayToExpand) {

        String[] arrOfNewData = new String[3];
        Scanner scan = new Scanner(System.in);

        System.out.println("Please add task description");
        String description = scan.nextLine();

        System.out.println("Please add task due date");
        String date = scan.nextLine();

        System.out.println("Is your task important: true/false");
        String importance = scan.nextLine();

        arrOfNewData[0] = description;
        arrOfNewData[1] = date;
        arrOfNewData[2] = importance;

        arrayToExpand = Arrays.copyOf(arrayToExpand, arrayToExpand.length + 1);
        arrayToExpand[arrayToExpand.length - 1] = arrOfNewData;

        return arrayToExpand;
    }

    public static String[][] remove(String[][] arrayToReduce) {

        Scanner scan = new Scanner(System.in);

        int numberToRemove = -1;

        System.out.println("Please select number to remove");
        while (numberToRemove < 0 || numberToRemove >= arrayToReduce.length) {
            try {
                numberToRemove = scan.nextInt();
                while (numberToRemove < 0 || numberToRemove >= arrayToReduce.length) {
                    System.out.println(ConsoleColors.RED + "Incorrect number passed. " + ConsoleColors.RESET + "Please give number that appears in the list");
                    numberToRemove = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED + "Incorrect argument passed. " + ConsoleColors.RESET + "Please give number greater or equal 0");
                scan.nextLine();
            }
        }

        arrayToReduce = ArrayUtils.remove(arrayToReduce, numberToRemove);
        System.out.println("Value was successfully deleted.");

        return arrayToReduce;
    }


    public static void saveToFile(String[][] arrToSave, String filename) {

        File file = new File(filename);

        try (FileWriter fw = new FileWriter(file, false)) {
            for (int i = 0; i < arrToSave.length; i++) {
                String line = arrToSave[i][0] + "," + arrToSave[i][1] + "," + arrToSave[i][2];
                fw.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}

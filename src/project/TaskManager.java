package project;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import pl.coderslab.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class TaskManager {
    static final String FILE_NAME = "tasks.txt";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;

    //(String[] args)
    public static void main(String[] args) {
        tasks = loadDataToTab(FILE_NAME);
        instruction();
        printOptions(OPTIONS);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                    opinion();
                    System.out.println(ConsoleColors.RED + "Bye, bye.");
                    System.exit(0);
                    break;

                case "add":
                    addTask();
                    saveTabToFile(FILE_NAME, tasks);
                    break;

                case "remove":
                    removeTask(tasks, getTheNumber());
                    System.out.println("Value was successfully deleted.");
                    saveTabToFile(FILE_NAME, tasks);
                    break;

                case "list":
                    printTab(tasks);
                    break;

                default:
                    System.out.println("Please select a correct option.");

            }
            printOptions(OPTIONS);
        }

    }

    public static void printOptions(String[] tab) {

        System.out.println(ConsoleColors.BLUE);
        System.out.println("Please select an option: " + ConsoleColors.RESET);

        for (String option : tab) {
            System.out.println(option);
        }

    }

    public static String[][] loadDataToTab(String fileName) {

        Path dir = Paths.get(fileName);

        if (!Files.exists(dir)) {
            System.out.println("File not exist.");
            System.exit(0);
        }
        String[][] tab = null;

        try {
            List<String> strings = Files.readAllLines(dir);
            tab = new String[strings.size()][strings.get(0).split(",").length];

            for (int i = 0; i < strings.size(); i++) {
                String[] split = strings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    tab[i][j] = split[j];
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }

    public static void printTab(String[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + " : ");

            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please add task description");
        String description = scanner.nextLine();

        System.out.println("Please add task due date");
        String dueDate = scanner.nextLine();

        System.out.println("Is your task important: true/false");
        String isImportant = scanner.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length - 1] = new String[3];
        tasks[tasks.length - 1][0] = description;
        tasks[tasks.length - 1][1] = dueDate;
        tasks[tasks.length - 1][2] = isImportant;
    }

    public static boolean isNumberGreaterEqualZero(String input) {

        if (NumberUtils.isParsable(input)) {
            return Integer.parseInt(input) >= 0;
        }
        return false;
    }

    public static int getTheNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select number to remove.");
        String n = scanner.nextLine();

        while (!isNumberGreaterEqualZero(n)) {
            System.out.println("Incorrect argument passed. Please give number greater or equal 0");
            scanner.nextLine();
        }
        return Integer.parseInt(n);
    }

    private static void removeTask(String[][] tab, int index) {
        try {
            if (index < tab.length) {
                tasks = ArrayUtils.remove(tab, index);
            }

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Element not exist in tab");
        }
    }


    public static void saveTabToFile(String fileName, String[][] tab) {
        Path dir = Paths.get(fileName);

        String[] lines = new String[tasks.length];

        for (int i = 0; i < tab.length; i++) {
            lines[i] = String.join(",", tab[i]);
        }
        try {
            Files.write(dir, Arrays.asList(lines));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void instruction() {
        System.out.println(pl.coderslab.ConsoleColors.GREEN + "If you select 'add' option, program will let you add task to file.");
        System.out.println("If you select 'remove' option, program will let you remove task from file.");
        System.out.println("If you select 'list' option, program will show you how does file like.");
        System.out.print(pl.coderslab.ConsoleColors.RESET);
    }

    public static void opinion() {
        System.out.println(ConsoleColors.PURPLE + "Do you like this program? Yes/no");
        Scanner s = new Scanner(System.in);
        String answear = s.nextLine();
        switch (answear) {
            case "Yes":
                System.out.println("Thanks :)"); break;
            case "yes":
                System.out.println("Thanks :)"); break;
            case "no":
                System.out.println(":("); break;
            case "No":
                System.out.println(":("); break;
            default:
                System.out.println("U didnt answear at question, but u didnt have to.");
        }
    }
}

















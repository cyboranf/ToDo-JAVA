package project;

import pl.coderslab.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) throws FileNotFoundException {
        instruction();
        options();
        Scanner scan = new Scanner(System.in);
        String input = "";
        input = scan.nextLine();
        while (!input.equals("exit")) {
            switch (input) {
                case "add":
                    addTask();
                    break;
                case "remove":
                    //removeTask();
                    break;
                case "list":
                    listTask();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Wrong option.");
                    System.out.print(ConsoleColors.RESET);
            }
            options();
            input = scan.nextLine();
        }
        System.out.println("Program ended."+ConsoleColors.RED+" Bye, bye");
        System.out.print(ConsoleColors.RESET);
    }

    public static void options() {
        System.out.println(pl.coderslab.ConsoleColors.BLUE + "Please select an option:");
        System.out.println(pl.coderslab.ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    public static void instruction() {
        System.out.println(pl.coderslab.ConsoleColors.GREEN + "If you select 'add' option, program will let you add task to file.");
        System.out.println("If you select 'remove' option, program will let you remove task from file.");
        System.out.println("If you select 'list' option, program will show you how does file like.");
        System.out.print(pl.coderslab.ConsoleColors.RESET);
    }

    public static void addTask() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String[] nameTask = new String[1];
        String[] date = new String[1];
        String[] howImportant = new String[1];
        int x = 0;

        String end = "";
        while (!end.equals("Yes")) {

            System.out.println("Please add task description:");
            nameTask[x] = scan.nextLine();

            System.out.println("Please add task due date:");
            date[x] = scan.nextLine();

            System.out.println("Is your task is important? True/False:");
            howImportant[x] = scan.nextLine();


            System.out.println("Do u want end TaskManager? Yes/No:");

            end = scan.nextLine();
            x++;
        }
        for (int i = 0; i < nameTask.length; i++) {
            PrintWriter printWriter = new PrintWriter("tasks.txt");
            printWriter.println(nameTask[i] + ", " + date[i] + ", " + howImportant[i]);
            printWriter.close();
        }
        System.out.println("Adding is ended.");
    }

    public static void remove() {
//        File file=new File("tasks.txt");
//        try(Scanner input=new Scanner(file)){
//            while(input.hasNextLine()){
//                String lineOfText= input.nextLine();
//                String[] tabOfWords=lineOfText.split("[ ,]+");
//                boolean test=false;
//
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Nie znalazłem pliku.");
//        }
    }

    public static void listTask() {
        File file = new File("tasks.txt");
        try {
            Scanner sc = new Scanner(file);
            String row = "";
            while (sc.hasNextLine()) {
                row = sc.nextLine();
                if (!row.equals("")) {
                    System.out.println(row);
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku");
        }

    }

}


package project;

import pl.coderslab.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

//(String[] args)
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
        opinion();
    }
    //prosi o opinie: spoko/nie spoko
    public static void opinion(){
        System.out.println(ConsoleColors.PURPLE+"Do you like this program? Yes/no");
        Scanner s=new Scanner(System.in);
        String answear=s.nextLine();
        if (answear.equals("Yes")){
            System.out.println("Thanks :)");
        }
        if (answear.equals("no")){
            if (answear.equals("No")){
                System.out.println(":(");
            }
        }
    }

    //wyswietla opcje ktore moze wybrac uzytkownik
    public static void options() {
        System.out.println(pl.coderslab.ConsoleColors.BLUE + "Please select an option:");
        System.out.println(pl.coderslab.ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    //start programu- tlumaczy za co dana opcja odpowiada
    public static void instruction() {
        System.out.println(pl.coderslab.ConsoleColors.GREEN + "If you select 'add' option, program will let you add task to file.");
        System.out.println("If you select 'remove' option, program will let you remove task from file.");
        System.out.println("If you select 'list' option, program will show you how does file like.");
        System.out.print(pl.coderslab.ConsoleColors.RESET);
    }

    //poprawic calkiem !
    public static void addTask() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        String[][] nameTask = new String[3][3];
        String[][] date = new String[3][3];
        String[][] howImportant = new String[3][3];
        int x = 0;

        String end = "";
        while (!end.equals("Yes")) {
            for (int i = 0; i < nameTask.length; i++) {
                System.out.println("Please add task description:");
                nameTask[i][x] = scan.nextLine();

                System.out.println("Please add task due date:");
                date[i][x] = scan.nextLine();

                System.out.println("Is your task is important? True/False:");
                howImportant[i][x] = scan.nextLine();



            }
            System.out.println("Do u want end TaskManager? Yes/No:");
            end = scan.nextLine();
            x++;
        }
        for (int i = 0; i < nameTask.length; i++) {
            for (int j = 0; j <nameTask[i].length; j++) { //j=x
                PrintWriter printWriter = new PrintWriter("tasks.txt");
                printWriter.println(nameTask[i][j] + ", " + date[i][j] + ", " + howImportant[i][j]);
                printWriter.close();
            }
                //STWORZYC JEDNA TABLICE DWUWYMIAROWA, A NIE 3 I SPROBOWAC

        }
        System.out.println("Adding is ended.");
    }

    //napisac raz jeszcze !
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

    //wyswietla plik w ktorym powinny/znajduja sie taski
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
                    System.out.println(ConsoleColors.RED+"txt hasnt tasks, if u want u can add.");
                    System.out.println(ConsoleColors.RESET);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku");
        }

    }

}


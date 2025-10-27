package BD1;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Registration reg = new Registration();

        printHeader("Мэдээллээ амжилттай авлаа. ");
        reg.loadSubjects("src/BD1/database/Subjects.txt");
        reg.loadMajors("src/BD1/database/Professions.txt");
        reg.loadStudents("src/BD1/database/Exams.txt");
        System.out.println(GREEN + "✔ Бүх мэдээллийг амжилттай авлаа!\n" + RESET);

        boolean running = true;
        while (running) {
            printMenu();

            System.out.print(YELLOW + "Сонголтоо хийгээрэй: " + RESET);
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    printHeader("Бүх хичээл");
                    reg.showAllSubjects();
                    break;
                case "2":
                    printHeader("Бүх мэргэжил");
                    reg.showAllMajors();
                    break;
                case "3":
                    printHeader("Бүх сурагчид");
                    reg.showAllStudents();
                    break;
                case "4":
                    printHeader("Хичээл дээр унасан сурагчид");
                    reg.showFailedStudents();
                    break;
                case "5":
                    printHeader("Хичээл тус бүрийн дүн");
                    reg.showGradesBySubject();
                    break;
                case "6":
                    printHeader("Мэргэжил тус бүрийн дүн");
                    reg.showGradesByMajor();
                    break;
                case "0":
                    printFooter();
                    running = false;
                    break;
                default:
                    System.out.println(RED + "⚠ Дахин оролдоно уу" + RESET);
            }

            if (running) {
                System.out.println("\n " + CYAN + "Enter" + RESET + " дээр дарж MENU рүү буцна уу...");
                sc.nextLine();
            }
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println(BLUE + "===============================================" + RESET);
        System.out.println(BOLD + CYAN + "             Сурагчдын дүнгийн мэдээлэл" + RESET);
        System.out.println(BLUE + "===============================================" + RESET);
        System.out.println(GREEN + "1." + RESET + " Бүх хичээлийг харах");
        System.out.println(GREEN + "2." + RESET + " Бүх мэргэжлийг харах");
        System.out.println(GREEN + "3." + RESET + " Бүх сурагчдыг харах");
        System.out.println(GREEN + "4." + RESET + " Хичээл дээр унасан сурагчдыг харах");
        System.out.println(GREEN + "5." + RESET + " Хичээл тус бүрийн дүнг харах");
        System.out.println(GREEN + "6." + RESET + " Мэргэжил тус бүрийн дүн харах");
        System.out.println(GREEN + "0." + RESET + " Гарах");
        System.out.println(BLUE + "-----------------------------------------------" + RESET);
    }

    private static void printHeader(String title) {
        String line = "===============================================";
        System.out.println("\n" + BLUE + line);
        System.out.println(BOLD + CYAN + "▶ " + title + RESET);
        System.out.println(BLUE + line + RESET);
    }

    private static void printFooter() {
        System.out.println("\n" + YELLOW + "-----------------------------------------------");
        System.out.println(BOLD + GREEN + "✔ Баярлалаа баяртай!" + RESET);
        System.out.println(YELLOW + "-----------------------------------------------\n" + RESET);
    }
}

import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException {
        StudentManagement management = new StudentManagement();
        boolean quit = false;
        do {
            createMenu();
            Scanner input = new Scanner(System.in);
            String choose = input.next();
            switch (choose) {
                case "1" -> management.addStudent();
                case "2" -> management.modifyInfo();
                case "3" -> management.addMarks();
                case "4" -> management.modifyMarks();
                case "5" -> management.displayStudentInfo();
                case "6" -> management.sortByName();
                case "7" -> management.removeStudent();
                case "8" -> management.deleteAll();
                case "9" -> {
                    System.out.println("Exited Successfully!");
                    quit = true;
                }
                default -> System.out.println("Enter a valid option!");
            }
        } while (!quit);
    }
    public static void createMenu(){
        System.out.println();
        System.out.println("----Menu----");
        System.out.println("1. Add new student");
        System.out.println("2. Modify student info");
        System.out.println("3. Add marks");
        System.out.println("4. Modify student marks");
        System.out.println("5. Display student profile");
        System.out.println("6. Sort student by name");
        System.out.println("7. Remove student");
        System.out.println("8. Delete all entries");
        System.out.println("9. Exit");
        System.out.print("Enter your desired action: ");
    }
}


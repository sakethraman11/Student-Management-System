import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        StudentManagement management = new StudentManagement();
        boolean quit = false;
        while(true) {
            createMenu();
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch(choose) {
                case "1":{
                    management.addStudent();
                    break;}
                case "2":{
                    management.modifyInfo();
                    break;
                }
                case "3":{
                    management.addMarks();
                    break;
                }
                case "4":{
                    management.modifyMarks();
                    break;
                }
                case "5":{
                    management.displayStudentInfo();
                    break;
                }
                case "6":{
                    management.sortByName();
                    break;
                }
                case "7":{
                    management.removeStudent();
                    break;
                }
                case "8":{
                    System.out.println("Exited Successfully!");
                    quit = true;
                    break;
                }
                default:{
                    System.out.println("Enter a valid option!");
                }
            }
            if(quit) {
                break;
            }
        }
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
        System.out.println("8. Exit");
        System.out.print("Enter your desired action: ");
    }
}
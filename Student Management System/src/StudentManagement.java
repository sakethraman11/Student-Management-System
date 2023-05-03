import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class StudentManagement  extends Student {
    Scanner sc=new Scanner(System.in);
    Scanner in=new Scanner(System.in);

    public void addStudent(){
        try {
            System.out.print("Enter student id: ");
            int id = in.nextInt();
            System.out.println();

            System.out.print("Enter student name: ");
            String name=in.next();
            System.out.println();

            System.out.print("Enter student surname: ");
            String surname=in.next();
            System.out.println();

            System.out.print("Enter student age: ");
            int age=in.nextInt();
            System.out.println();

            System.out.print("Student date of birth: ");
            System.out.println("Enter year:");
            int year=in.nextInt();
            System.out.println("Enter month:");
            int month=in.nextInt();
            System.out.println("Enter date:");
            int date=in.nextInt();
            LocalDate dob =LocalDate.of(year,month,date);
            System.out.println();

            System.out.print("Enter gender[M]/[F]: ");
            char gender=in.next().charAt(0);
            System.out.println();

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentportal", "root", "saketh");
            Statement statement = connection.createStatement();

            String sql="insert into studentinfo"+"(id,name,surname,age,dob,gender)"+"values('"+id+"','"+name+"','"+surname+"','"+age+"','"+dob+"','"+gender+"')";
            statement.executeUpdate(sql);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addMarks(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentportal", "root", "saketh");
            Statement statement = connection.createStatement();
            System.out.println("Enter student id: ");
            int id= in.nextInt();
            String sql="SELECT*from studentinfo WHERE id='"+id+"'";
            ResultSet resultSet=statement.executeQuery(sql);

            if (resultSet.next()){
                System.out.print("Enter maths marks: ");
                float maths=in.nextFloat();
                System.out.println();

                System.out.print("Enter physics marks: ");
                float physics=in.nextFloat();
                System.out.println();

                System.out.print("Enter chemistry marks: ");
                float chemistry=in.nextFloat();
                System.out.println();

                System.out.print("Enter english marks: ");
                float english=in.nextFloat();
                System.out.println();

                System.out.print("Enter java marks: ");
                float java=in.nextFloat();
                System.out.println();

                String query="insert into marks"+"(id,maths,physics,chemistry,english,java)"+"values('"+id+"','"+maths+"','"+physics+"','"+chemistry+"','"+english+"','"+java+"')";
                statement.executeUpdate(query);
            }
            else {
                System.out.println("Student not present in database! Enter a valid student id!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void modifyInfo(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentportal", "root", "saketh");
            Statement statement = connection.createStatement();
            System.out.println("Enter student id: ");
            int id= in.nextInt();
            String sql="SELECT*from studentinfo WHERE id='"+id+"'";
            ResultSet resultSet=statement.executeQuery(sql);
            boolean done=false;
            if (resultSet.next()){
                while (!done){
                    System.out.println("What do you want to change? \n 1.Student name \n 2.Student surname \n 3.Student age \n 4.Student date of birth \n 5.Student gender \n 6.Exit");
                    String choose=in.next();
                    switch (choose){
                        case "1":{
                            System.out.print("Enter new name: ");
                            String newName=in.next();
                            String query="UPDATE studentinfo SET name='"+newName+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "2":{
                            System.out.print("Enter new surname: ");
                            String newSurname=in.next();
                            String query="UPDATE studentinfo SET surname='"+newSurname+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "3":{
                            System.out.print("Enter new age: ");
                            int newAge=in.nextInt();
                            String query="UPDATE studentinfo SET age='"+newAge+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "4":{
                            System.out.print("Enter year: ");
                            int newYear=in.nextInt();
                            System.out.print("Enter month: ");
                            int newMonth=in.nextInt();
                            System.out.print("Enter date: ");
                            int newDate=in.nextInt();
                            LocalDate newDob=LocalDate.of(newYear,newMonth,newDate);
                            String query="UPDATE studentinfo SET dob='"+newDob+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "5":{
                            System.out.println("Enter gender");
                            char newGender=in.next().charAt(0);
                            String query="UPDATE studentinfo SET gender='"+newGender+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "6":{
                            done=true;
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void modifyMarks(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentportal", "root", "saketh");
            Statement statement = connection.createStatement();
            System.out.println("Enter student id: ");
            int id= in.nextInt();
            String sql="SELECT*from marks WHERE id='"+id+"'";
            ResultSet resultSet=statement.executeQuery(sql);
            boolean done=false;

                if (resultSet.next()){
                    while (!done){
                    System.out.println("What do you want to change? \n 1.Maths \n 2.Physics \n 3.Chemistry \n 4.English \n 5.Java \n 6.Exit");
                    String choose=in.next();
                    switch (choose){
                        case "1":{
                            System.out.print("Enter new maths mark: ");
                            float newMaths=in.nextFloat();
                            String query="UPDATE marks SET maths='"+newMaths+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "2":{
                            System.out.print("Enter new physics mark: ");
                            float newPhysics=in.nextFloat();
                            String query="UPDATE marks SET physics='"+newPhysics+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "3":{
                            System.out.print("Enter new chemistry mark: ");
                            float newChemistry=in.nextFloat();
                            String query="UPDATE marks SET chemistry='"+newChemistry+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "4":{
                            System.out.print("Enter new english mark: ");
                            float newEnglish=in.nextFloat();
                            String query="UPDATE marks SET english='"+newEnglish+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "5":{
                            System.out.print("Enter new java mark: ");
                            float newJava=in.nextFloat();
                            String query="UPDATE marks SET java='"+newJava+"' WHERE id='"+id+"'";
                            statement.executeUpdate(query);
                            break;
                        }
                        case "6":{
                            done=true;
                            break;
                        }
                    }
                }

            }
                else {
                    System.out.println("Enter a valid student id!");
                }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void displayStudentInfo(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentportal", "root", "saketh");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from studentinfo");
            while (resultSet.next()) {
                System.out.print("Student ID: ");
                System.out.print(resultSet.getString("id"));
                System.out.print(", Name: ");
                System.out.print(resultSet.getString("name"));
                System.out.print(", Surname: ");
                System.out.print(resultSet.getString("surname"));
                System.out.print(", Age: ");
                System.out.print(resultSet.getString("age"));
                System.out.print(", Date of Birth(yyyy-mm-dd): ");
                System.out.print(resultSet.getString("dob"));
                System.out.print(", Gender: ");
                System.out.print(resultSet.getString("gender"));
                System.out.println();
            }
            resultSet=statement.executeQuery("Select*from marks");
            while (resultSet.next()){
                System.out.print("Maths mark: ");
                System.out.print(resultSet.getString("maths"));
                System.out.print(", Physics mark: ");
                System.out.print(resultSet.getString("physics"));
                System.out.print(", Chemistry mark: ");
                System.out.print(resultSet.getString("chemistry"));
                System.out.print(", English mark: ");
                System.out.print(resultSet.getString("english"));
                System.out.print(", Java mark: ");
                System.out.print(resultSet.getString("java"));
                System.out.println();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sortByName(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentportal", "root", "saketh");
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * from studentinfo ORDER by name");
            while (resultSet.next()) {
                    System.out.print("Student ID: ");
                    System.out.print(resultSet.getString("id"));
                    System.out.print(", Name: ");
                    System.out.print(resultSet.getString("name"));
                    System.out.print(", Surname: ");
                    System.out.print(resultSet.getString("surname"));
                    System.out.print(", Age: ");
                    System.out.print(resultSet.getString("age"));
                    System.out.print(", Date of Birth(yyyy-mm-dd): ");
                    System.out.print(resultSet.getString("dob"));
                    System.out.print(", Gender: ");
                    System.out.print(resultSet.getString("gender"));
                    System.out.println();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void removeStudent() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentportal", "root", "saketh");
            System.out.println("Enter student id: ");
            int remId=sc.nextInt();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM studentinfo WHERE id="+remId+";");
            preparedStatement.executeUpdate();
            System.out.println("Student number "+remId+" have been removed!");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}

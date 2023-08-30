import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class StudentManagement extends EntryCheckException{
    String dbUrl = "jdbc:mysql://localhost:3306/studentportal";
    String dbUser = "root";
    String dbPass = "saketh";
    Scanner input = new Scanner(System.in);

    public void studentIDValidation(){
        while (!input.hasNextInt()){
            try {
                throw new InvalidIDException();
            }
            catch (InvalidIDException message) {
                System.out.println(message);
                System.out.print("Please enter only numbers for Student ID:");
                input.next();
            }
        }
    }

    public void studentNameValidation(){
        while (!input.hasNext("[a-zA-Z]+")){
            try {
                throw new InvalidNameException();
            }
            catch (InvalidNameException message){
                System.out.println(message);
                System.out.println("Please enter only alphabets for names");
                input.next();
            }
        }
    }
    public void studentGenderValidation(){
        while (!input.hasNext("[a-zA-Z]+")){
            try {
                throw new InvalidGenderException();
            }
            catch (InvalidGenderException message){
                System.out.println(message);
                System.out.println("Please enter only M/F for gender");
                input.next();
            }
        }
    }

    public void studentNumberInputValidation(){
        while (!input.hasNextInt()){
            try {
                throw new InvalidNumberInputException();
            }
            catch (InvalidNumberInputException message) {
                System.out.println(message);
                System.out.print("Please enter only numbers for age:");
                input.next();
            }
        }
    }

    public  void studentMarkValidation(){
        while (!input.hasNextFloat()){
            try {
                throw new InvalidMarkException();
            }
            catch (InvalidMarkException message) {
                System.out.println(message);
                System.out.print("Please enter only numbers for marks:");
                input.next();
            }
        }
    }

    public void addStudent() {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {

            PreparedStatement preparedStatement;
            PreparedStatement preparedStatementCheck;
            ResultSet resultSet;

            System.out.print("Enter student id: ");
            studentIDValidation();
            int id = input.nextInt();
            System.out.println();

            preparedStatementCheck = connection.prepareStatement("select id from studentinfo where id=?");
            preparedStatementCheck.setInt(1, id);
            resultSet = preparedStatementCheck.executeQuery();

            if (!resultSet.next()) {
                System.out.print("Enter student name: ");
                studentNameValidation();
                String name = input.next();
                System.out.println();

                System.out.print("Enter student surname: ");
                studentNameValidation();
                String surname = input.next();
                System.out.println();

                System.out.print("Enter student age: ");
                studentNumberInputValidation();
                int age = input.nextInt();
                System.out.println();

                System.out.print("Student date of birth: ");
                System.out.println("Enter year:");
                studentNumberInputValidation();
                int year = input.nextInt();
                System.out.println("Enter month:");
                studentNumberInputValidation();
                int month = input.nextInt();
                System.out.println("Enter date:");
                studentNumberInputValidation();
                int date = input.nextInt();
                LocalDate dob = LocalDate.of(year, month, date);
                System.out.println();

                String gender = input.nextLine();
                while (!gender.equals("M") && !gender.equals("F")) {
                    System.out.println("Enter a valid gender[M]/[F]: ");
                    gender = input.nextLine();
                }

                System.out.println();
                System.out.println("Student added to database!");

                String sql = "insert into studentinfo(id,name,surname,age,dob,gender) values(?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, surname);
                preparedStatement.setInt(4, age);
                preparedStatement.setObject(5, dob);
                preparedStatement.setString(6, gender);
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Student ID already exists!");
            }

        }
        catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
    }

    public void addMarks() throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {

            PreparedStatement preparedStatement;
            PreparedStatement preparedStatementCheck;
            ResultSet resultSet;

            System.out.println("Enter student id: ");
            studentIDValidation();
            int id = input.nextInt();
            preparedStatementCheck = connection.prepareStatement("SELECT*from studentinfo WHERE id=?");
            preparedStatementCheck.setInt(1, id);
            resultSet = preparedStatementCheck.executeQuery();

            if (resultSet.next()) {
                System.out.print("Enter maths marks: ");
                studentMarkValidation();
                float maths = input.nextFloat();
                System.out.println();

                System.out.print("Enter physics marks: ");
                studentMarkValidation();
                float physics = input.nextFloat();
                System.out.println();

                System.out.print("Enter chemistry marks: ");
                studentMarkValidation();
                float chemistry = input.nextFloat();
                System.out.println();

                System.out.print("Enter english marks: ");
                studentMarkValidation();
                float english = input.nextFloat();
                System.out.println();

                System.out.print("Enter java marks: ");
                studentMarkValidation();
                float java = input.nextFloat();
                System.out.println();

                String query = "insert into marks(id,maths,physics,chemistry,english,java) values(?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setFloat(1, id);
                preparedStatement.setFloat(2, maths);
                preparedStatement.setFloat(3, physics);
                preparedStatement.setFloat(4, chemistry);
                preparedStatement.setFloat(5, english);
                preparedStatement.setFloat(6, java);
                preparedStatement.executeUpdate();
            } else {
                System.out.println("Student not present in database! Enter a valid student id!");
            }
        }
        catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
    }

    public void modifyInfo() throws SQLException {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {

            PreparedStatement preparedStatementCheck;
            PreparedStatement preparedStatement;
            ResultSet resultSet;

            System.out.println("Enter student id: ");
            studentIDValidation();
            int id = input.nextInt();
            preparedStatementCheck = connection.prepareStatement("SELECT*from studentinfo WHERE id=?");
            preparedStatementCheck.setInt(1, id);
            resultSet = preparedStatementCheck.executeQuery();
            boolean done = false;
            if (resultSet.next()) {
                while (!done) {
                    System.out.println("What do you want to change? \n 1.Student name \n 2.Student surname \n 3.Student age \n 4.Student date of birth \n 5.Student gender \n 6.Exit");
                    String choose = input.next();
                    switch (choose) {
                        case "1" -> {
                            System.out.print("Enter new name: ");
                            studentNameValidation();
                            String newName = input.next();
                            preparedStatement = connection.prepareStatement("UPDATE studentinfo SET name=? WHERE id=?");
                            preparedStatement.setString(1, newName);
                            preparedStatement.setInt(2, id);
                            preparedStatement.executeUpdate();
                            System.out.println();
                        }
                        case "2" -> {
                            System.out.print("Enter new surname: ");
                            studentNameValidation();
                            String newSurname = input.next();
                            preparedStatement = connection.prepareStatement("UPDATE studentinfo SET surname=? WHERE id=?");
                            preparedStatement.setString(1, newSurname);
                            preparedStatement.setInt(2, id);
                            preparedStatement.executeUpdate();
                            System.out.println();
                        }
                        case "3" -> {
                            System.out.print("Enter new age: ");
                            studentNumberInputValidation();
                            int newAge = input.nextInt();
                            preparedStatement = connection.prepareStatement("UPDATE studentinfo SET age=? WHERE id=?");
                            preparedStatement.setInt(1, newAge);
                            preparedStatement.setInt(2, id);
                            preparedStatement.executeUpdate();
                            System.out.println();
                        }
                        case "4" -> {
                            System.out.print("Enter year: ");
                            studentNumberInputValidation();
                            int newYear = input.nextInt();
                            System.out.print("Enter month: ");
                            studentNumberInputValidation();
                            int newMonth = input.nextInt();
                            System.out.print("Enter date: ");
                            studentNumberInputValidation();
                            int newDate = input.nextInt();
                            LocalDate newDob = LocalDate.of(newYear, newMonth, newDate);
                            preparedStatement = connection.prepareStatement("UPDATE studentinfo SET dob=? WHERE id=?");
                            preparedStatement.setObject(1, newDob);
                            preparedStatement.setInt(2, id);
                            preparedStatement.executeUpdate();
                            System.out.println();
                        }
                        case "5" -> {
                            String newGender = input.nextLine();
                            while (!newGender.equals("M") && !newGender.equals("F")) {
                                System.out.println("Enter a valid gender: ");
                                newGender = input.nextLine();
                            }
                            preparedStatement = connection.prepareStatement("UPDATE studentinfo SET gender=? WHERE id=?");
                            preparedStatement.setString(1, newGender);
                            preparedStatement.setInt(2, id);
                            preparedStatement.executeUpdate();
                            System.out.println();
                        }
                        case "6" -> {
                            done = true;
                        }
                    }
                }
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
    }

    public void modifyMarks() throws SQLException,NullPointerException {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement preparedStatement=null;
            PreparedStatement preparedStatementCheck;
            ResultSet resultSet=null;
            System.out.println("Enter student id: ");
            studentIDValidation();
            int id = input.nextInt();
            preparedStatementCheck = connection.prepareStatement("SELECT*from marks WHERE id=?");
            preparedStatementCheck.setInt(1, id);
            resultSet = preparedStatementCheck.executeQuery();
            boolean done = false;
            if (resultSet.next()) {
                while (!done) {
                    System.out.println("What do you want to change? \n 1.Maths \n 2.Physics \n 3.Chemistry \n 4.English \n 5.Java \n 6.Exit");
                    String choose = input.next();
                    switch (choose) {
                        case "1" -> {
                            System.out.print("Enter new maths mark: ");
                            studentMarkValidation();
                            float newMaths = input.nextFloat();
                            String query = "UPDATE marks SET maths=? WHERE id=?";
                            preparedStatement.executeUpdate(query);
                            preparedStatement.setFloat(2, newMaths);
                            preparedStatement.setInt(1, id);
                        }
                        case "2" -> {
                            System.out.print("Enter new physics mark: ");
                            studentMarkValidation();
                            float newPhysics = input.nextFloat();
                            String query = "UPDATE marks SET physics=? WHERE id=?";
                            preparedStatement.executeUpdate(query);
                            preparedStatement.setFloat(3, newPhysics);
                            preparedStatement.setInt(1, id);
                        }
                        case "3" -> {
                            System.out.print("Enter new chemistry mark: ");
                            studentMarkValidation();
                            float newChemistry = input.nextFloat();
                            String query = "UPDATE marks SET chemistry=? WHERE id=?";
                            preparedStatement.executeUpdate(query);
                            preparedStatement.setFloat(4, newChemistry);
                            preparedStatement.setInt(1, id);
                        }
                        case "4" -> {
                            System.out.print("Enter new english mark: ");
                            studentMarkValidation();
                            float newEnglish = input.nextFloat();
                            String query = "UPDATE marks SET english=? WHERE id=?";
                            preparedStatement.executeUpdate(query);
                            preparedStatement.setFloat(5, newEnglish);
                            preparedStatement.setInt(1, id);
                        }
                        case "5" -> {
                            System.out.print("Enter new java mark: ");
                            studentMarkValidation();
                            float newJava = input.nextFloat();
                            String query = "UPDATE marks SET java=? WHERE id=?";
                            preparedStatement.executeUpdate(query);
                            preparedStatement.setFloat(6, newJava);
                            preparedStatement.setInt(1, id);
                        }
                        case "6" -> {
                            done = true;
                        }
                    }
                }

            } else {
                System.out.println("Enter a valid student id!");
            }
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }

    public void displayStudentInfo() throws SQLException {
        try(Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement preparedStatement1;
            PreparedStatement preparedStatement2;
            ResultSet resultSet;
            preparedStatement1=connection.prepareStatement("select * from studentinfo");
            resultSet = preparedStatement1.executeQuery();
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
            preparedStatement2=connection.prepareStatement("Select*from marks");
            resultSet = preparedStatement2.executeQuery();
            while (resultSet.next()) {
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
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }

    public void sortByName() throws SQLException {
        try(Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            preparedStatement=connection.prepareStatement("SELECT * from studentinfo ORDER by name");
            resultSet = preparedStatement.executeQuery();
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
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
    }

    public void removeStudent() throws SQLException {
        try(Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass)) {
            PreparedStatement preparedStatement;
            System.out.println("Enter student id: ");
            studentIDValidation();
            int remId = input.nextInt();
            preparedStatement = connection.prepareStatement("DELETE FROM studentinfo WHERE id=?");
            preparedStatement.setInt(1,remId);
            preparedStatement.executeUpdate();
            System.out.println("Student number " + remId + " have been removed!");
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
    }

    public void deleteAll() throws SQLException{
        try(Connection connection=DriverManager.getConnection(dbUrl,dbUser,dbPass)) {
            PreparedStatement preparedStatement;
            preparedStatement=connection.prepareStatement("Truncate table studentinfo");
            preparedStatement.executeUpdate();
            preparedStatement=connection.prepareStatement("Truncate table marks");
            preparedStatement.executeUpdate();
        }catch (SQLException sqle){
            System.out.println(sqle.getMessage());
        }
    }
}


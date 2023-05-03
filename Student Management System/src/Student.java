import java.time.LocalDate;

public class Student {
    private int id;
    private String name;

    private String surname;

    private int age;

    private LocalDate dob;
    private char gender;

//    public Student(int id, String name, String surname, int age, LocalDate dob, char gender){
//        super();
//        this.id=id;
//        this.name=name;
//        this.surname=surname;
//        this.age=age;
//        this.dob=dob;
//        this.gender=gender;
//
//    }

    public int setId(int id){
        return this.id=id;
    }
    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public void setSurname(String surname){
        this.surname=surname;
    }

    public String getSurname(){
        return surname;
    }

    public int setAge(int age){
        return this.age=age;
    }

    public int getAge(){
        return age;
    }

    public LocalDate setDob(LocalDate dob){
        return this.dob=dob;
    }

    public LocalDate getDob(){
        return dob;
    }

    public char setGender(char gender){
        return this.gender=gender;
    }

    public char getGender(){
        return gender;
    }


}

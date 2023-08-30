public class EntryCheckException {
    public class InvalidIDException extends Exception{
        public InvalidIDException(){
            super("Invalid ID!");
        }
    }
    public class InvalidNameException extends Exception{
        public InvalidNameException(){
            super("Invalid Name!");
        }
    }

    public class InvalidNumberInputException extends Exception{
        public InvalidNumberInputException(){
            super("Invalid Age!");
        }
    }

    public class InvalidMarkException extends Exception{
        public InvalidMarkException(){
            super("Invalid Mark");
        }
    }

    public class InvalidGenderException extends Exception{
        public InvalidGenderException(){
            super("Invalid Gender");
        }
    }
}

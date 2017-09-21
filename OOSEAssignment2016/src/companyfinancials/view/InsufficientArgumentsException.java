package companyfinancials.view;

public class InsufficientArgumentsException extends RuntimeException{
    public InsufficientArgumentsException(){
        super();
    }

    public InsufficientArgumentsException(String message){
        super(message);
    }
}

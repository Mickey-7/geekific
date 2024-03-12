package custom_exception;

public class AgeLessThanZeroException extends Exception {
    public AgeLessThanZeroException(){}
    public AgeLessThanZeroException(String message){
        super(message);
    }

    public AgeLessThanZeroException(Throwable throwable){
        super(throwable);
    }

    public AgeLessThanZeroException(String message, Throwable throwable){
        super(message, throwable);
    }
}

package exception;

public class AttributeException extends Exception{

    public AttributeException() {
        super();
    }
    

    public AttributeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttributeException(String message) {
        super(message);
    }
}

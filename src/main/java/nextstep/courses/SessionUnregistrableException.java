package nextstep.courses;

public class SessionUnregistrableException extends RuntimeException{
    public SessionUnregistrableException(){
    }

    public SessionUnregistrableException(String message) {
        super(message);
    }
}

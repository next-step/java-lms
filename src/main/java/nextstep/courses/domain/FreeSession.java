package nextstep.courses.domain;

public class FreeSession extends SessionType{

    public FreeSession(boolean isPaid) {
        super(isPaid);
    }

    @Override
    public boolean isEnrollmentPossible(Integer size) {
        return true;
    }
}

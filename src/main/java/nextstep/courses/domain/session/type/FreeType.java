package nextstep.courses.domain.session.type;

public class FreeType implements SessionType {

    @Override
    public SessionType enroll(long payAmount) {
        return this;
    }
}
package nextstep.courses.domain;

import nextstep.courses.ExceedMaxStudentException;
import nextstep.courses.domain.type.SessionStatus;

public class ChargedSession extends Session {

    private final int maxNumberOfStudent;
    private int numberOfStudent;

    public static ChargedSession init(Duration duration, Image image, int maxNumberOfStudent) {
        return new ChargedSession(duration, image, SessionStatus.READY, maxNumberOfStudent);
    }

    private ChargedSession(Duration duration, Image image, SessionStatus status, int maxNumberOfStudent) {
        super(duration, image, status);
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public void addStudent() {
        validateMaxNumberOfStudent();
        this.numberOfStudent++;
    }

    private void validateMaxNumberOfStudent() {
        if (this.maxNumberOfStudent == this.numberOfStudent) {
            throw new ExceedMaxStudentException("수강 인원을 초과했습니다.");
        }
    }

}

package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionType;
import nextstep.payments.domain.Payment;

public class FreeSession extends Session {
    private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";

    public static FreeSession createNewInstance(Course course, SessionInfos sessionInfos) {
        return new FreeSession(0L, course, sessionInfos, 0);
    }

    public static FreeSession createFromData(Long id, Course course, SessionInfos sessionInfos, int numberOfStudents) {
        return new FreeSession(id, course, sessionInfos, numberOfStudents);
    }

    private FreeSession(Long id, Course course, SessionInfos sessionInfos, int numberOfStudents) {
        super(id, course, sessionInfos, SessionType.FREE, numberOfStudents);
    }

    @Override
    public void enroll(Payment payment) {
        if (sessionInfos.isStatusNotRecruiting()) {
            throw new IllegalStateException(SESSION_NOT_RECRUITING);
        }
        numberOfStudents++;
    }
}

package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public class FreeSession extends Session {
    private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";

    public static FreeSession createNewInstance(Course course, SessionInfos sessionInfos, CoverImageInfo coverImageInfo) {
        return new FreeSession(0L, course, sessionInfos, 0, coverImageInfo);
    }

    public static FreeSession createFromData(Long id, Course course, SessionInfos sessionInfos, int numberOfStudents, CoverImageInfo coverImageInfo) {
        return new FreeSession(id, course, sessionInfos, numberOfStudents, coverImageInfo);
    }

    private FreeSession(Long id, Course course, SessionInfos sessionInfos, int numberOfStudents, CoverImageInfo coverImageInfo) {
        super(id, course, sessionInfos, numberOfStudents, coverImageInfo);
    }

    @Override
    public void enroll(Payment payment) {
        if (sessionInfos.isStatusNotRecruiting()) {
            throw new IllegalStateException(SESSION_NOT_RECRUITING);
        }
        numberOfStudents++;
    }
}

package nextstep.courses.domain;

import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import nextstep.payments.domain.Payment;

public class FreeSession extends Session {
    private static final String SESSION_NOT_RECRUITING = "해당 강의는 현재 모집 중이 아닙니다.";

    private FreeSession(Long id, Course course, SessionDate sessionDate, SessionStatus sessionStatus, int numberOfStudents, CoverImageInfo coverImageInfo, SessionType type) {
        super(id, course, sessionDate, sessionStatus, numberOfStudents, coverImageInfo, type);
    }

    @Override
    public void enroll(Payment payment) {
        if (sessionStatus.isStatusNotRecruiting()) {
            throw new IllegalStateException(SESSION_NOT_RECRUITING);
        }
        numberOfStudents++;
    }

    public static FreeSession.FreeSessionBuilder builder() {
        return new FreeSession.FreeSessionBuilder();
    }

    public static class FreeSessionBuilder {
        private Long id;
        private Course course;
        private SessionDate sessionDate;
        private SessionStatus sessionStatus;
        private int numberOfStudents;
        private CoverImageInfo coverImageInfo;
        private SessionType type;

        public FreeSession.FreeSessionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FreeSession.FreeSessionBuilder course(Course course) {
            this.course = course;
            return this;
        }

        public FreeSession.FreeSessionBuilder sessionDate(SessionDate sessionDate) {
            this.sessionDate = sessionDate;
            return this;
        }

        public FreeSession.FreeSessionBuilder sessionStatus(SessionStatus sessionStatus) {
            this.sessionStatus = sessionStatus;
            return this;
        }

        public FreeSession.FreeSessionBuilder numberOfStudents(int numberOfStudents) {
            this.numberOfStudents = numberOfStudents;
            return this;
        }

        public FreeSession.FreeSessionBuilder coverImageInfo(CoverImageInfo coverImageInfo) {
            this.coverImageInfo = coverImageInfo;
            return this;
        }

        public FreeSession.FreeSessionBuilder type(SessionType type) {
            this.type = type;
            return this;
        }

        public FreeSession build() {
            return new FreeSession(id, course, sessionDate, sessionStatus, numberOfStudents, coverImageInfo, type);
        }
    }
}

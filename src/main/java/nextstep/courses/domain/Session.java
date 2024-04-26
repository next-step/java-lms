package nextstep.courses.domain;

import java.time.LocalDateTime;
import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public class Session {

    private final long id;
    private String title;
    private SessionDuration sessionDuration;
    private SessionType sessionType;
    private SessionState state;
    private Image image;
    private Enrollment enrollment;
    private Course course;

    private Session(Builder builder) {
        id = builder.id;
        title = builder.title;
        sessionDuration = builder.sessionDuration;
        sessionType = builder.sessionType;
        state = builder.state;
        image = builder.image;
        enrollment = builder.enrollment;
        course = builder.course;
    }

    public void toCourse(Course course) {
        this.course = course;
    }

    public void register(NsUser user) throws CannotRegisterException {
        isRegisteredAllowed();
        this.enrollment.enroll(user);
    }

    public void register(NsUser user, Payment payment) throws CannotRegisterException {
        isRegisteredAllowed();
        this.enrollment.enroll(user, payment);
    }

    public boolean hasId(Long id) {
        return this.id == id;
    }

    public int countOfEnrolledStudent() {
        return enrollment.countOfEnrolledStudent();
    }

    private void isRegisteredAllowed() throws CannotRegisterException {
        if (!state.isAllowed()) {
            throw new CannotRegisterException(String.format("해당 강의 상태 %s에서는 수강 신청을 할 수 없습니다.", state.getState()));
        }
    }

    public static class Builder {
        private final long id;
        private String title;
        private SessionDuration sessionDuration;
        private SessionType sessionType;
        private SessionState state;
        private Image image;
        private Course course;
        private Enrollment enrollment;

        public Builder(long id) {
            this.id = id;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder state(SessionState state) {
            this.state = state;
            return this;
        }

        public Builder sessionDuration(LocalDateTime startDate, LocalDateTime endDate) {
            this.sessionDuration = new SessionDuration(startDate, endDate);
            return this;
        }

        public Builder sessionType(SessionType sessionType) {
            this.sessionType = sessionType;
            return this;
        }

        public Builder image(Image image) {
            this.image = image;
            return this;
        }

        public Builder enrollment(Enrollment enrollment) {
            this.enrollment = enrollment;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }
}

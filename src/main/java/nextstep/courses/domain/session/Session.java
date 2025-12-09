package nextstep.courses.domain.session;

import nextstep.courses.SessionUnregistrableException;
import nextstep.courses.domain.*;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.session.type.Free;
import nextstep.courses.domain.session.type.Paid;
import nextstep.courses.domain.session.type.SessionType;

import java.time.LocalDate;

public class Session {
    private final BaseEntity baseEntity;
    private final Image image;
    private SessionStatus status;
    private final SessionType type;
    private final SessionEnrollment enrollment;

    public Session(Long id, String title, Image image, SessionStatus status, SessionType type, SessionEnrollment enrollment, LocalDate startDate, LocalDate endDate) {
        this.baseEntity = new BaseEntity(id, title, new Period(startDate, endDate));
        this.image = image;
        this.status = status;
        this.type = type;
        this.enrollment = enrollment;
    }

    public void openRecruiting() {
        this.status = SessionStatus.RECRUITING;
    }

    public void enroll(EnrollmentCondition request) {
        checkEnrollable(request);

        this.enrollment.enroll(request.getUser());
    }

    private void checkEnrollable(EnrollmentCondition condition) {
        if (!isRecruiting()) {
            throw new SessionUnregistrableException(String.format("%s 상태인 강의는 수강신청할 수 없습니다.", this.status.name()));
        }
        if (!this.type.canEnroll(condition)) {
            throw new SessionUnregistrableException(String.format("%s 강의 수강 신청 조건 미달로 신청할 수 없습니다.", this.type.toHumanReadableTypeName()));
        }
    }

    private boolean isRecruiting() {
        return this.status == SessionStatus.RECRUITING;
    }

    public static Session createFreeSession(String title, Image image) {
        return new Session(null, title, image, SessionStatus.PREPARING, new Free(), new SessionEnrollment(), LocalDate.now(), LocalDate.MAX);
    }

    public static Session createPaidSession(long id, String title, Image image, int maxCapacity, long price) {
        return new Session(null, title, image, SessionStatus.PREPARING, new Paid(id, price), new SessionEnrollment(maxCapacity), LocalDate.now(), LocalDate.MAX);
    }
}

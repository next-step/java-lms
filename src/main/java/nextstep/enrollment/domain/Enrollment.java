package nextstep.enrollment.domain;

import java.util.Objects;

import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private Long id;

    private Session session;

    private NsUser attendee;

    public Enrollment(final Session session, final NsUser attendee) {
        this.session = session;
        this.attendee = attendee;
    }

    public void enroll(final long paymentPrice) {
        validate(paymentPrice);
        session.getEnrollments().add(this);
    }

    private void validate(final long price) {
        session.validateNonFreeSession();
        session.validatePriceEquality(price);
        validateAttendeeNumber();
        validateRecruitingStatus();
        validateAlreadyEnroll();
    }

    private void validateAttendeeNumber() {
        if (session.isFull()) {
            throw new IllegalArgumentException("수강 인원이 초과되었습니다.");
        }
    }

    private void validateRecruitingStatus() {
        if (session.isNotRecruiting()) {
            throw new IllegalArgumentException("모집중인 강의가 아닙니다.");
        }
    }

    private void validateAlreadyEnroll() {
        if (session.canEnroll(this)) {
            throw new IllegalArgumentException("이미 수강 신청한 사용자입니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Enrollment that = (Enrollment) o;
        return Objects.equals(session, that.session) && Objects.equals(attendee, that.attendee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, attendee);
    }
}

package nextstep.sessions.domain;

import nextstep.courses.domain.Course;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import nextstep.sessions.exception.CannotEnrollException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.time.LocalDateTime;

public class Session {
    private final Long id;

    private final Course course;

    private final String title;

    private final Period period;

    private final CoverImage coverImage;

    private final SessionStatus sessionStatus;

    private final SessionType sessionType;

    private final NsUsers students;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public static Session freeSession(Long id, Course course, String title, Period period, CoverImage coverImage, SessionStatus sessionStatus, NsUsers students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Session(id, course, title, period, coverImage, sessionStatus, new FreeSessionType(), students, createdAt, updatedAt);
    }

    public static Session paidSession(Long id, Course course, String title, Period period, CoverImage coverImage, SessionStatus sessionStatus, int capacity, long price, NsUsers students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Session(id, course, title, period, coverImage, sessionStatus, new PaidSessionType(capacity, new Money(price)), students, createdAt, updatedAt);
    }

    public Session(Long id, Course course, String title, Period period, CoverImage coverImage, SessionStatus sessionStatus, SessionType sessionType, NsUsers students, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.period = period;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
        this.students = students;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void assertCanEnroll(NsUser requestUser) {
        if (!sessionStatus.canRecruit()) {
            throw new CannotEnrollException("현재 모집중인 강의가 아닙니다.");
        }

        if (students.contains(requestUser)) {
            throw new CannotEnrollException("이미 신청한 강의입니다.");
        }

        if (sessionType.isFull(students.size())) {
            throw new CannotEnrollException("현재 수강 인원이 가득 찼습니다.");
        }
    }

    public void enroll(NsUser requestUser, Payment payment) {
        assertPayOnExactSession(payment);
        this.students.add(requestUser);
    }

    public void assertPayOnExactSession(Payment payment) {
        if (!payment.sameSessionId(this.id)) {
            throw new CannotEnrollException("결제 정보와 강의 정보가 일치하지 않습니다.");
        }

        if (!sessionType.equalsPrice(payment)) {
            throw new CannotEnrollException("결제 금액과 강의 금액이 일치하지 않습니다.");
        }
    }

    public Long id() {
        return this.id;
    }

    public Money price() {
        return this.sessionType.price();
    }


}

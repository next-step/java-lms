package nextstep.courses.domain.session;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.enums.SessionProcessStatus;
import nextstep.courses.domain.session.enums.SessionRecruitStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Session {

    private Long id;
    private Period period;
    private SessionStatus status;
    private SessionRecruitStatus recruitStatus;
    private SessionProcessStatus processStatus;
    private Students students;
    private SessionType sessionType;
    private SessionImage sessionImage;
    private Course course;

    public Session(SessionImage image) throws PeriodException {
        this(null, new Period(LocalDate.now(), LocalDate.now().plusDays(1L)), SessionStatus.WAITING, new Students(), new SessionType(), image);
    }
    public Session(SessionStatus status) throws PeriodException {
        this(null, new Period(LocalDate.now(), LocalDate.now().plusDays(1L)), status, new Students(), new SessionType(), new SessionImage());
    }

    public Session(SessionStatus sessionStatus, LocalDate startDate, LocalDate endDate) throws PeriodException {
        this(null, new Period(startDate, endDate), sessionStatus, new Students(), new SessionType(), new SessionImage());
    }

    public Session(SessionStatus status, Set<NsUser> nsUsers, SessionType sessionType) throws PeriodException {
        this(null, new Period(LocalDate.now(), LocalDate.now().plusDays(1L)), status, new Students(nsUsers), sessionType, new SessionImage());
    }

    public Session(Period period, SessionStatus status, Students students, SessionType sessionType, SessionImage image) {
        this(null, period, status, students, sessionType, image);
    }

    public Session(Long id, Period period, SessionStatus status, Students students, SessionType sessionType, SessionImage sessionImage) {
        this.id = id;
        this.period = period;
        this.status = status;
        this.students = students;
        this.sessionType = sessionType;
        if (sessionImage == null) {
            throw new IllegalArgumentException("이미지 정보는 반드시 담겨야 합니다");
        }
        this.sessionImage = sessionImage;
    }

    public Session(Period period, SessionStatus sessionStatus, SessionType sessionType) {
        this.period = period;
        this.status = sessionStatus;
        this.sessionType = sessionType;
    }

    public Students register(NsUser user) throws CannotRegisterException {
        return register(user, new Payment());
    }

    public Students register(NsUser user, Payment payment) throws CannotRegisterException {
        if (isRecruitOpen()) {
            throw new CannotRegisterException("모집중이 아닌 경우 신청이 불가합니다");
        }

        if (!sessionType.isEqualPrice(payment)) {
            throw new CannotRegisterException("강의 금액과 일치하지 않습니다.");
        }

        students.registerSessionStudent(user, sessionType);
        return students;
    }

    private boolean isRecruitOpen() {
        return !SessionRecruitStatus.isOpen(status) && !SessionRecruitStatus.isOpen(recruitStatus);
    }

    public Session updateStatus(SessionStatus status) throws PeriodException {
        this.status = period.validate(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(period, session.period) && status == session.status && Objects.equals(sessionType, session.sessionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(period, status, sessionType);
    }

    public long getId() {
        return id;
    }

    public Students getStudents() {
        return students;
    }

    public LocalDate startedAt() {
        return this.period.startDate();
    }

    public LocalDate endAt() {
        return this.period.endDate();
    }

    public String status() {
        return this.status.name();
    }

    public String payType() {
        return this.sessionType.type().name();
    }

    public Long price() {
        return this.sessionType.price();
    }

    public int capacity() {
        return this.sessionType.capacity();
    }

    public Long courseId() {
        return this.course.getId();
    }

    public Long imageId() {
        return this.sessionImage.getId();
    }

    public void addCourse(Course course) {
        this.course = course;
    }
}

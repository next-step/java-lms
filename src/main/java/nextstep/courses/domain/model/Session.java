package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Session extends BaseEntity {
    private final Course course;
    private SessionPeriod period;
    private final List<SessionImage> images;
    private final SessionStatus status;//    강의 진행 상태(준비중, 진행중, 종료)와 모집 상태(비모집중, 모집중)로 상태 값을 분리해야 한다.
    private final RecruitmentStatus recruitmentStatus;

    private Long price;
    private final Students students;
    private final Long creatorId;

    private Session(Long id, Course course, SessionPeriod period, SessionImage image, SessionStatus status, RecruitmentStatus recruitmentStatus, Long price, int capacity, Long creatorId) {
        this(id, course, period, image, status, recruitmentStatus, price, capacity, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(Long id, Course course, SessionPeriod period, SessionImage image, SessionStatus status, RecruitmentStatus recruitmentStatus, Long price, int capacity, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, course, period, Collections.singletonList(image), status, recruitmentStatus, price, new Students(capacity), creatorId, createdAt, updatedAt);
    }


    public Session(Long id, Course course, SessionPeriod period, List<SessionImage> images, SessionStatus status, RecruitmentStatus recruitmentStatus, Long price, Students students, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.course = course;
        this.period = period;
        this.images = images;
        this.status = status;
        this.recruitmentStatus = recruitmentStatus;
        this.price = price;
        this.students = students;
        this.creatorId = creatorId;
    }


    public static Session createFreeSession(Course course, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, SessionStatus status, RecruitmentStatus recruitmentStatus, NsUser creator) {
        Session session = new Session(null, course, new SessionPeriod(startDate, endDate), image, status, recruitmentStatus, 0L, Integer.MAX_VALUE, creator.getId());
        course.addSession(session);
        return session;
    }

    public static Session createPaidSession(Course course, SessionPeriod period, SessionImage image, SessionStatus status, RecruitmentStatus recruitmentStatus, Long price, int capacity, NsUser creator) {
        Session session = new Session(null, course, period, image, status, recruitmentStatus, price, capacity, creator.getId());
        course.addSession(session);
        return session;
    }

    public Student enroll(NsUser user) {
        if (course.hasSelection()) {
            throw new IllegalArgumentException("session has selection process");
        }

        if (recruitmentStatus != RecruitmentStatus.ON) {
            throw new IllegalArgumentException("session is not open");
        }

        return students.register(user, this, price);
    }


    public Payment getPayment(NsUser user) {
        if (!students.include(user)) {
            throw new IllegalArgumentException("student not found");
        }
        return new Payment("0L", id, user.getId(), price);
    }

    public Course getCourse() {
        return course;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public Long getPrice() {
        return price;
    }

    public Students getStudents() {
        return students;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public List<SessionImage> getImages() {
        return images;
    }

}

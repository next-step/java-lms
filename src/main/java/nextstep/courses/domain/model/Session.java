package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final List<NsUser> applicants;
    private final List<NsUser> selected;
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
        this.applicants = new ArrayList<>();
        this.selected = new ArrayList<>();
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

    private Student enroll(NsUser user) {
        selected.remove(user);

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

    public void apply(NsUser user) {
        if (recruitmentStatus != RecruitmentStatus.ON) {
            throw new IllegalArgumentException("session is not open");
        }

        if (applicants.contains(user) || students.include(user)) {
            throw new IllegalArgumentException("already applied");
        }

        applicants.add(user);

        if (!course.hasSelection()) {
            select(user);
            enroll(user);
        }
    }

    public List<NsUser> getApplicants() {
        return Collections.unmodifiableList(applicants);
    }

    public List<NsUser> getSelected() {
        return Collections.unmodifiableList(selected);
    }

    public void select(NsUser user) {
        if (!applicants.contains(user)) {
            throw new IllegalArgumentException("not an applicant");
        }
        applicants.remove(user);
        selected.add(user);
    }

    public Student approve(NsUser user) {
        if (!selected.contains(user)) {
            throw new IllegalArgumentException("not selected");
        }

        return enroll(user);
    }

    public void cancel(NsUser user) {
        if (selected.contains(user)) {
            throw new IllegalArgumentException("not an applicant");
        }

        if (!applicants.contains(user)) {
            throw new IllegalArgumentException("not an applicant");
        }

        applicants.remove(user);
    }
}

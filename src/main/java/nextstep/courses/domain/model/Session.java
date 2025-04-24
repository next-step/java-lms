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
    private final SessionStatus status;
    private Long price;
    private final Recruitment recruitment;
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
        this(id, course, period, images, status, new Recruitment(recruitmentStatus, students), price, creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Course course, SessionPeriod period, List<SessionImage> images, SessionStatus status, Recruitment recruitment, Long price, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.course = course;
        this.period = period;
        this.images = images;
        this.status = status;
        this.recruitment = recruitment;
        this.applicants = new ArrayList<>();
        this.selected = new ArrayList<>();
        this.price = price;
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

    public void apply(NsUser user) {
        recruitment.apply(user);

        if (!course.hasSelection()) {
            recruitment.select(List.of(user));
            recruitment.enroll(user, this, price);
        }
    }

    public int select(NsUser user) {
        return recruitment.select(List.of(user));
    }

    public int select(SelectStrategy strategy) {
        return recruitment.select(strategy);
    }

    public Student approve(NsUser user) {
        return recruitment.approve(user, this, price);
    }

    public void cancel(NsUser user) {
        recruitment.cancel(user);
    }

    public Payment getPayment(NsUser user) {
        return recruitment.getPayment(user, this, price);
    }

    public StudentStatus getStudentStatus(NsUser user) {
        return recruitment.getStudentStatus(user);
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
        return recruitment.getStatus();
    }

    public Long getPrice() {
        return price;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Students getStudents() {
        return recruitment.getStudents();
    }

    public List<SessionImage> getImages() {
        return images;
    }

}

package nextstep.courses.domain.model;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Session extends BaseEntity {
    private final Course course;
    private final List<SessionImage> images;
    private final SessionStatus status;
    private final Recruitment recruitment;
    private final Long creatorId;
    private final SessionPeriod period;
    private final Long price;

    private Session(Long id, Course course, SessionPeriod period, SessionImage image, SessionStatus status, RecruitmentStatus recruitmentStatus, Long price, int capacity, Long creatorId) {
        this(id, course, period, image, status, recruitmentStatus, price, capacity, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(Long id, Course course, SessionPeriod period, SessionImage image, SessionStatus status, RecruitmentStatus recruitmentStatus, Long price, int capacity, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, course, period, Collections.singletonList(image), status, recruitmentStatus, price, new Applicants(capacity), creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Course course, SessionPeriod period, List<SessionImage> images, SessionStatus status, RecruitmentStatus recruitmentStatus, Long price, Applicants applicants, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, course, period, images, status, new Recruitment(recruitmentStatus, applicants), price, creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Course course, SessionPeriod period, List<SessionImage> images, SessionStatus status, Recruitment recruitment, Long price, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.course = course;
        this.period = period;
        this.images = images;
        this.status = status;
        this.recruitment = recruitment;
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
        recruitment.apply(user, this, price);

        if (!course.hasSelection()) {
            recruitment.select(user);
            recruitment.approve(user);
        }
    }

    public void select(NsUser user) {
        recruitment.select(user);
    }

    public int select(SelectStrategy strategy) {
        return recruitment.select(strategy);
    }

    public void approve(NsUser user) {
        recruitment.approve(user);
    }

    public void cancel(NsUser user) {
        recruitment.cancel(user);
    }

    public ApplicantStatus getStudentStatus(NsUser user) {
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

    public Applicants getStudents() {
        return recruitment.getStudents();
    }

    public List<SessionImage> getImages() {
        return images;
    }

}

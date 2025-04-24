package nextstep.courses.domain.model;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Session extends BaseEntity {
    private final Course course;
    private final List<SessionImage> images;
    private final ProgressStatus status;
    private final Registration registration;
    private final Long creatorId;
    private final SessionPeriod period;
    private final Long price;

    private Session(Long id, Course course, SessionPeriod period, SessionImage image, ProgressStatus status, RegistrationStatus registrationStatus, Long price, int capacity, Long creatorId) {
        this(id, course, period, image, status, registrationStatus, price, capacity, creatorId, LocalDateTime.now(), LocalDateTime.now());
    }

    public Session(Long id, Course course, SessionPeriod period, SessionImage image, ProgressStatus status, RegistrationStatus registrationStatus, Long price, int capacity, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, course, period, Collections.singletonList(image), status, registrationStatus, price, new Registration(capacity), creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Course course, SessionPeriod period, List<SessionImage> images, ProgressStatus status, RegistrationStatus registrationStatus, Long price, Registration registration, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, course, period, images, status, new Registration(registrationStatus, new HashSet<>(), registration.getCapacity()), price, creatorId, createdAt, updatedAt);
    }

    public Session(Long id, Course course, SessionPeriod period, List<SessionImage> images, ProgressStatus status, Registration registration, Long price, Long creatorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.course = course;
        this.period = period;
        this.images = images;
        this.status = status;
        this.registration = registration;
        this.price = price;
        this.creatorId = creatorId;
    }

    public static Session createFreeSession(Course course, LocalDateTime startDate, LocalDateTime endDate, SessionImage image, ProgressStatus status, RegistrationStatus registrationStatus, NsUser creator) {
        Session session = new Session(null, course, new SessionPeriod(startDate, endDate), image, status, registrationStatus, 0L, Integer.MAX_VALUE, creator.getId());
        course.addSession(session);
        return session;
    }

    public static Session createPaidSession(Course course, SessionPeriod period, SessionImage image, ProgressStatus status, RegistrationStatus registrationStatus, Long price, int capacity, NsUser creator) {
        Session session = new Session(null, course, period, image, status, registrationStatus, price, capacity, creator.getId());
        course.addSession(session);
        return session;
    }

    public void apply(NsUser user) {
        registration.apply(user, this, price);

        if (!course.hasSelection()) {
            registration.select(user);
            registration.approve(user);
        }
    }

    public void select(NsUser user) {
        registration.select(user);
    }

    public int select(SelectStrategy strategy) {
        return registration.select(strategy);
    }

    public void approve(NsUser user) {
        registration.approve(user);
    }

    public void cancel(NsUser user) {
        registration.cancel(user);
    }

    public ApplicantStatus getStudentStatus(NsUser user) {
        return registration.getApplicantStatus(user);
    }

    public Course getCourse() {
        return course;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public ProgressStatus getStatus() {
        return status;
    }

    public RegistrationStatus getRecruitmentStatus() {
        return registration.getStatus();
    }

    public Long getPrice() {
        return price;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public int getCapacity() {
        return registration.getCapacity();
    }

    public List<SessionImage> getImages() {
        return images;
    }

}

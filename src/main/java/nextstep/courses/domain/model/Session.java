package nextstep.courses.domain.model;

import nextstep.users.domain.NsUser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Session extends BaseEntity {
    private final Course course;
    private final List<SessionImage> images;
    private final ProgressStatus status;
    private final Registration registration;
    private final SessionPeriod period;
    private final Long price;
    private final Long creatorId;

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
        return new Session(null, course, new SessionPeriod(startDate, endDate), image, status, registrationStatus, 0L, Integer.MAX_VALUE, creator.getId());
    }

    public static Session createPaidSession(Course course, SessionPeriod period, SessionImage image, ProgressStatus status, RegistrationStatus registrationStatus, Long price, int capacity, NsUser creator) {
        return new Session(null, course, period, image, status, registrationStatus, price, capacity, creator.getId());
    }

    public void apply(NsUser user) {
        registration.apply(user, getId(), price);

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


    public Map<String, Object> getParameters() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("course_id", course.getId());
        parameters.put("capacity", getCapacity());
        parameters.put("status", getStatus().name());
        parameters.put("recruitment", getRecruitmentStatus().name());
        parameters.put("price", new BigDecimal(price));
        parameters.put("start_date", period.getStartDate().toLocalDate());
        parameters.put("end_date", period.getEndDate().toLocalDate());
        parameters.put("creator_id", creatorId);
        parameters.put("created_at", getCreatedAt());
        parameters.put("updated_at", getUpdatedAt());
        return parameters;
    }

}

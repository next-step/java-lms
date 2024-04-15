package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.course.Course;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;
import static nextstep.courses.ExceptionMessage.SESSION_ENROLL_FAIL_MESSAGE;
import static nextstep.courses.domain.session.SessionGatheringStatus.NON_GATHERING;
import static nextstep.courses.domain.session.SessionStatus.PREPARING;

public class Session {
    private Long id;
    private String title;
    private String description;
    private SessionType sessionType;
    private SessionStatus sessionStatus;
    private SessionGatheringStatus sessionGatheringStatus;
    private Period periodOfSession;
    private CoverImage coverImage;
    private CoverImages coverImages;
    private Course course;
    private EnrolledUsers enrolledUsers = new EnrolledUsers();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(String title, String description, SessionType sessionType, Period periodOfSession, CoverImage coverImage, Course course) {
        this(null, title, description, sessionType, PREPARING, NON_GATHERING, periodOfSession, coverImage, course, LocalDateTime.now(), null);
    }

    public Session(String title, String description, SessionType sessionType, Period periodOfSession, CoverImages coverImages, Course course) {
        this(null, title, description, sessionType, PREPARING, NON_GATHERING, periodOfSession, coverImages, course, LocalDateTime.now(), null);
    }

    public Session(Long id, String title, String description, SessionType sessionType, Period periodOfSession, CoverImage coverImage, Course course) {
        this(id, title, description, sessionType, PREPARING, NON_GATHERING, periodOfSession, coverImage, course, LocalDateTime.now(), null);
    }

    public Session(Long id, String title, String description, SessionType sessionType, Period periodOfSession, CoverImages coverImages, Course course) {
        this(id, title, description, sessionType, PREPARING, NON_GATHERING, periodOfSession, coverImages, course, LocalDateTime.now(), null);
    }

    public Session(Long id, String title, String description, SessionType sessionType, SessionStatus sessionStatus, SessionGatheringStatus sessionGatheringStatus, Period periodOfSession, CoverImage coverImage, Course course, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.sessionGatheringStatus = sessionGatheringStatus;
        this.periodOfSession = periodOfSession;
        this.coverImage = coverImage;
        this.course = course;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session(Long id, String title, String description, SessionType sessionType, SessionStatus sessionStatus, SessionGatheringStatus sessionGatheringStatus, Period periodOfSession, CoverImages coverImages, Course course, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
        this.sessionGatheringStatus = sessionGatheringStatus;
        this.periodOfSession = periodOfSession;
        this.coverImages = coverImages;
        this.course = course;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void enroll(NsUser user, Payment payment) {
        validateSessionEnrollment(user, payment);
        addEnrolledUser(user);
    }

    private void validateSessionEnrollment(NsUser user, Payment payment) {
        if (!isSessionEnrollPossible(user, payment)) {
            throw new CannotEnrollException(SESSION_ENROLL_FAIL_MESSAGE.message());
        }
    }

    private boolean isSessionEnrollPossible(NsUser user, Payment payment) {
        return sessionGatheringStatus.isEnrollPossibleStatus() &&
                !enrolledUsers.isDuplicatedUser(user) &&
                sessionType.isEnrollmentPossible(enrolledUsers.numberOfCurrentEnrollment(), payment);
    }

    private void addEnrolledUser(NsUser user) {
        enrolledUsers.add(user);
    }

    public void updateStatusAs(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateSessionGatheringStatusAs(SessionGatheringStatus sessionGatheringStatus) {
        this.sessionGatheringStatus = sessionGatheringStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateAsSavedSession(Long id) {
        if (nonNull(this.id)) {
            return;
        }
        this.id = id;
    }

    public void updateEnrolledUsers(EnrolledUsers enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public int getMaxNumberOfEnrollment() {
        return sessionType.getMaxNumberOfEnrollment();
    }

    public long getFee() {
        return sessionType.getFee();
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public SessionGatheringStatus getSessionGatheringStatus() {
        return sessionGatheringStatus;
    }

    public LocalDateTime getStartedAt() {
        return periodOfSession.getStartedAt();
    }

    public LocalDateTime getEndedAt() {
        return periodOfSession.getEndedAt();
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public Long getIdOfCoverImage() {
        return coverImage.getId();
    }

    public List<CoverImage> getCoverImages() {
        return coverImages.getCoverImages();
    }

    public Course getCourse() {
        return course;
    }

    public Long getIdOfCourse() {
        return course.getId();
    }

    public List<NsUser> getEnrolledUsers() {
        return enrolledUsers.getEnrolledUsers();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

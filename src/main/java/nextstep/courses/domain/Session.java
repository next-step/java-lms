package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private Long id;
    private SessionInfo sessionInfo;
    private SessionUsers sessionUsers;
    private SessionDate sessionDate;
    private SessionImage coverImage;
    private SessionPrice price;
    private SessionStatus status;
    private AuditTimestamp auditTimestamp;

    public Session() {
    }

    public Session(String title, Long creatorId, Course course, LocalDate startDate, LocalDate endDate,
                   String coverImageUrl, Long price, int maxNumberOfUsers,
                   List<SessionUser> registeredUsers) {
        this(0L, title, creatorId, course, startDate, endDate, coverImageUrl, price, SessionStatus.OPENED, maxNumberOfUsers,
                registeredUsers, LocalDateTime.now(), null);
    }

    public Session(Long id, String title, Long creatorId, Course course, LocalDate startDate, LocalDate endDate,
                   String coverImageUrl, Long price, SessionStatus status, int maxNumberOfUsers,
                   List<SessionUser> registeredUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(id, new SessionInfo(title, creatorId, course),
                new SessionUsers(maxNumberOfUsers, registeredUsers),
                new SessionDate(startDate, endDate), new SessionImage(coverImageUrl),
                new SessionPrice(price), status, createdAt, updatedAt);
    }

    public Session(Long id, SessionInfo sessionInfo, SessionUsers sessionUsers, SessionDate sessionDate,
                   SessionImage coverImage, SessionPrice price, SessionStatus status, LocalDateTime createdAt,
                   LocalDateTime updatedAt) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.sessionUsers = sessionUsers;
        this.sessionDate = sessionDate;
        this.coverImage = coverImage;
        this.price = price;
        this.status = status;
        this.auditTimestamp = new AuditTimestamp(createdAt, updatedAt);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return sessionInfo.getTitle();
    }

    public Long getCreatorId() {
        return sessionInfo.getCreatorId();
    }

    public Long getCourseId() {
        return sessionInfo.getCourseId();
    }

    public String getCoverImageUrl() {
        return coverImage.getUrl();
    }

    public LocalDate getStartDateTime() {
        return sessionDate.getStartDate();
    }

    public LocalDate getEndDateTime() {
        return sessionDate.getEndDate();
    }

    public SessionPayType getPayType() {
        return price.getPayType();
    }

    public Long getPrice() {
        return price.getPrice();
    }

    public SessionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return auditTimestamp.getCreatedAt();
    }

    public void enroll(Long userId) {
        if (!status.isRecruiting()) {
            throw new IllegalStateException("모집 중인 강의만 수강 신청이 가능합니다.");
        }
        sessionUsers.registerUser(userId);
        this.auditTimestamp.update();
    }

    public int countUsers() {
        return sessionUsers.countUsers();
    }

    public void open() {
        changeStatus(SessionStatus.OPENED);

    }

    public void close() {
        changeStatus(SessionStatus.CLOSED);
    }

    public void recruit() {
        changeStatus(SessionStatus.RECRUITING);
    }

    private void changeStatus(SessionStatus status) {
        this.status = status;
        this.auditTimestamp.update();
    }
}

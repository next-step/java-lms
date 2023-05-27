package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    private Long id;
    private String title;
    private Long creatorId;
    private Course course;
    private LocalDate startDate;
    private LocalDate endDate;
    private String coverImageUrl;
    private SessionPayType payType;
    private SessionStatus status;
    private int maxNumberOfUsers;
    private int numberOfUsers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session() {
    }

    public Session(String title, Long creatorId, Course course, LocalDate startDate, LocalDate endDate,
                   String coverImageUrl, SessionPayType payType, SessionStatus status, int maxNumberOfUsers,
                   int numberOfUsers) {
        this(0L, title, creatorId, course, startDate, endDate, coverImageUrl, payType, status, maxNumberOfUsers,
                numberOfUsers, LocalDateTime.now(), null);
    }

    public Session(Long id, String title, Long creatorId, Course course, LocalDate startDate, LocalDate endDate,
                   String coverImageUrl, SessionPayType payType, SessionStatus status, int maxNumberOfUsers,
                   int numberOfUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
        }
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImageUrl = coverImageUrl;
        this.payType = payType;
        this.status = status;
        this.maxNumberOfUsers = maxNumberOfUsers;
        this.numberOfUsers = numberOfUsers;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDate getStartDateTime() {
        return startDate;
    }

    public LocalDate getEndDateTime() {
        return endDate;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }
}

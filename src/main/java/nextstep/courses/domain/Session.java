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
    private SessionPrice price;
    private SessionStatus status;
    private int maxNumberOfUsers;
    private int numberOfUsers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session() {
    }

    public Session(String title, Long creatorId, Course course, LocalDate startDate, LocalDate endDate,
                   String coverImageUrl, Long price, SessionStatus status, int maxNumberOfUsers,
                   int numberOfUsers) {
        this(0L, title, creatorId, course, startDate, endDate, coverImageUrl, price, status, maxNumberOfUsers,
                numberOfUsers, LocalDateTime.now(), null);
    }

    public Session(Long id, String title, Long creatorId, Course course, LocalDate startDate, LocalDate endDate,
                   String coverImageUrl, Long price, SessionStatus status, int maxNumberOfUsers,
                   int numberOfUsers, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
        }
        if (!isValidUrl(coverImageUrl)) {
            throw new IllegalArgumentException("유효하지 않은 URL 입니다.");
        }
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.course = course;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImageUrl = coverImageUrl;
        this.price = new SessionPrice(price);
        this.status = status;
        this.maxNumberOfUsers = maxNumberOfUsers;
        this.numberOfUsers = numberOfUsers;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private boolean isValidUrl(String coverImageUrl) {
        try {
            new java.net.URL(coverImageUrl).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public SessionPayType getPayType() {
        return price.getPayType();
    }

    public Long getPrice() {
        return price.getPrice();
    }
}

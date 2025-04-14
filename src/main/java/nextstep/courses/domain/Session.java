package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private String title;
    private int id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long tuition;
    private int currentCount;
    private int capacity;
    private Image coverImage;
    private SessionStatus sessionStatus;
    private JoinStrategy joinStrategy;

    public Session(String title, int id, LocalDateTime startDate, LocalDateTime endDate, long tuition, int currentCount, int capacity, Image coverImage, SessionStatus sessionStatus, JoinStrategy joinStrategy) {

        this.title = title;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tuition = tuition;
        this.currentCount = currentCount;
        this.capacity = capacity;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.joinStrategy = joinStrategy;
    }

    boolean joinable(long paidAmount) {
        return joinStrategy.joinable(this, paidAmount);
    }

    public boolean recruiting() {
        return sessionStatus == SessionStatus.RECRUITING;
    }

    public boolean underCapacity() {
        return currentCount < capacity;
    }

    public boolean tuitionMatched(long paidAmount) {
        return this.tuition == paidAmount;
    }

    public boolean hasId(long id) {
        return this.id == id;
    }

    public void enroll(long payAmount) {
        if (!joinable(payAmount)) {
            throw new IllegalStateException("수강 신청 조건을 만족하지 않습니다.");
        }

        this.currentCount++;
    }
}

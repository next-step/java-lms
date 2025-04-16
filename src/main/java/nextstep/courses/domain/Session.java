package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Session {
    private CapacityInfo capacityInfo;
    private SessionPeriod sessionPeriod;
    private String title;
    private int id;
    private Long tuition;
    private Image coverImage;
    private SessionStatus sessionStatus;
    private JoinStrategy joinStrategy;


    public Session(String title, int id, LocalDateTime startDate, LocalDateTime endDate, Long tuition, int currentCount, int capacity, Image coverImage, SessionStatus sessionStatus) {
        this(title, id, startDate, endDate, tuition, currentCount, capacity, coverImage, sessionStatus, tuition == 0 ? new FreeJoinStrategy() : new PaidJoinStrategy());
    }

    public Session(String title, int id, LocalDateTime startDate, LocalDateTime endDate, Long tuition, int currentCount, int capacity, Image coverImage, SessionStatus sessionStatus, JoinStrategy joinStrategy) {
        this.title = title;
        this.id = id;
        this.sessionPeriod = new SessionPeriod(startDate, endDate);
        this.tuition = tuition;
        this.capacityInfo = new CapacityInfo(currentCount, capacity);
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.joinStrategy = joinStrategy;
    }

    boolean joinable(Payment pay) {
        return joinStrategy.joinable(this, pay);
    }

    public boolean recruiting() {
        return sessionStatus == SessionStatus.RECRUITING;
    }

    public boolean underCapacity() {
        return capacityInfo.getCurrentCount() < capacityInfo.getCapacity();
    }

    public boolean tuitionMatched(long paidAmount) {
        return this.tuition == paidAmount;
    }

    public boolean hasId(long id) {
        return this.id == id;
    }

    public void enroll(Payment pay) {
        if (!joinable(pay)) {
            throw new IllegalStateException("수강 신청 조건을 만족하지 않습니다.");
        }

        this.capacityInfo.increaseCurrentCount();
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public long getTuition() {
        return tuition;
    }

    public LocalDateTime getStartDate() {
        return sessionPeriod.getStartDate();
    }

    public LocalDateTime getEndDate() {
        return sessionPeriod.getEndDate();
    }

    public int getCurrentCount() {
        return capacityInfo.getCurrentCount();
    }

    public int getCapacity() {
        return capacityInfo.getCapacity();
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public SessionStatus getStatus() {
        return sessionStatus;
    }
}

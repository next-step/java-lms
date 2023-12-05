package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private final Long id;
    private final Duration duration;
    private final SessionType sessionType;
    private final SessionOpenStatus sessionOpenStatus;
    private final SessionProgressStatus sessionProgressStatus;
    private int maximumEnrollmentCount = 0;
    private final Price price;
    private final NsUsers nsUsers = new NsUsers();

    public Session(Long id,
                   LocalDateTime startDate,
                   LocalDateTime endDate,
                   SessionType sessionType,
                   SessionOpenStatus sessionOpenStatus,
                   SessionProgressStatus sessionProgressStatus, int maximumEnrollmentCount,
                   int fee) {
        inputValidation(sessionType, maximumEnrollmentCount);
        this.sessionProgressStatus = sessionProgressStatus;
        this.id = id;
        this.duration = new Duration(startDate, endDate);
        this.sessionType = sessionType;
        this.sessionOpenStatus = sessionOpenStatus;
        this.maximumEnrollmentCount = maximumEnrollmentCount;
        this.price = new Price(fee);
    }

    public Long id() {
        return id;
    }

    public LocalDateTime start_at() {
        return duration.startAt();
    }

    public LocalDateTime getEndAt() {
        return duration.endAt();
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public SessionOpenStatus getSessionStatus() {
        return sessionOpenStatus;
    }

    public int getMaximumEnrollmentCount() {
        return maximumEnrollmentCount;
    }

    public int getPrice() {
        return price.price();
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, SessionOpenStatus sessionOpenStatus, int maximumEnrollmentCount, int fee, SessionProgressStatus sessionProgressStatus) {
        this(0L, startDate, endDate, sessionType, sessionOpenStatus, sessionProgressStatus, maximumEnrollmentCount, fee);
    }

    private void inputValidation(SessionType sessionType,int maximumEnrollment) {
        if(sessionType.isFree() && maximumEnrollment != 0){
            throw new IllegalArgumentException("무료 세션은 최대 수강 인원이 없습니다.");
        }
    }

    private boolean canEnroll(Price paidFee){
        return (isOpened() || isInProgress()) && !isFullEnrollment() && possibleFee(paidFee);
    }

    private boolean isOpened(){
        return sessionOpenStatus.isOpened();
    }

    private boolean isInProgress(){
        return sessionProgressStatus.isInProgress();
    }

    private boolean isFullEnrollment() {
        return sessionType.isPaid() && nsUsers.isFullEnrollment(maximumEnrollmentCount);
    }

    private boolean possibleFee(Price paidFee){
        return sessionType.isPaid() && price.samePrice(paidFee);
    }

    public boolean enroll(NsUser nsUser, Price paidFee, LocalDateTime date) {
        if (!canEnroll(paidFee)) {
            throw new IllegalArgumentException("수강 신청이 불가능합니다.");
        }
        if(!isInProgress(date)){
            throw new IllegalArgumentException("수강 신청 기간이 아닙니다.");
        }
        return nsUsers.enroll(nsUser);
    }

    public boolean isInProgress(LocalDateTime date) {
        return duration.isInProgress(date);
    }
}

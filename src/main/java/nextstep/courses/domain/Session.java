package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Optional;

public class Session {

    private final Duration duration;
    private final SessionType sessionType;
    private Image coverImage;
    private final SessionStatus sessionStatus;
    private int maximumEnrollmentCount = 0;
    private final Price price;
    private final NsUsers nsUsers = new NsUsers();

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, Image coverImage, SessionStatus sessionStatus, int maximumEnrollmentCount, int fee) {
        inputValidation(sessionType, maximumEnrollmentCount);
        this.duration = new Duration(startDate, endDate);
        this.sessionType = sessionType;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.maximumEnrollmentCount = maximumEnrollmentCount;
        this.price = new Price(fee);
    }

    private void inputValidation(SessionType sessionType,int maximumEnrollment) {
        if(sessionType.isFree() && maximumEnrollment != 0){
            throw new IllegalArgumentException("무료 세션은 최대 수강 인원이 없습니다.");
        }
    }

    private boolean canEnroll(Price paidFee){
        return isOpened() && !isFullEnrollment() && possibleFee(paidFee);
    }

    private boolean isOpened(){
        return sessionStatus.isOpened();
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

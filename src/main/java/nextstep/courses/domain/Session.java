package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SessionType sessionType;
    private Image coverImage;
    private SessionStatus sessionStatus;
    private int maximumEnrollment = 0;
    private int fee;
    private List<NsUser> participants;


    public Session() {
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean canEnroll(int paidFee){
        return isOpened() && !isFullEnrollment() && possibleFee(paidFee);
    }

    private boolean isOpened(){
        return sessionStatus == SessionStatus.OPENED;
    }

    private boolean isFullEnrollment() {
        return sessionType == SessionType.PAID && participants.size() >= maximumEnrollment;
    }

    private boolean possibleFee(int paidFee){
        return sessionType == SessionType.PAID && fee == paidFee;
    }

}

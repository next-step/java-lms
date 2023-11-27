package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private SessionType sessionType;
    private Image coverImage;
    private SessionStatus sessionStatus;
    private int maximumEnrollment = 0;
    private int fee;
    private List<NsUser> participants = new ArrayList<>();

    public Session(LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, Image coverImage, SessionStatus sessionStatus, int maximumEnrollment, int fee) {

        inputValidation(startDate, endDate, sessionType, coverImage, sessionStatus, maximumEnrollment, fee);

        this.startDate = startDate;
        this.endDate = endDate;
        this.sessionType = sessionType;
        this.coverImage = coverImage;
        this.sessionStatus = sessionStatus;
        this.maximumEnrollment = maximumEnrollment;
        this.fee = fee;
    }

    private void inputValidation(LocalDateTime startDate, LocalDateTime endDate, SessionType sessionType, Image coverImage, SessionStatus sessionStatus, int maximumEnrollment, int fee) {
        if(sessionType == SessionType.FREE && maximumEnrollment != 0){
            throw new IllegalArgumentException("무료 세션은 최대 수강 인원이 없습니다.");
        }
    }

    private boolean canEnroll(int paidFee){
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

    public boolean enroll(NsUser nsUser, int paidFee) {
        if (!canEnroll(paidFee)) {
            throw new IllegalArgumentException("수강 신청이 불가능합니다.");
        }
        return participants.add(nsUser);
    }
}

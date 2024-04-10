package nextstep.sessions.domain;

import nextstep.sessions.domain.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private long id;

    private String sessionName;

    private Image image;

    //강의 상태(준비중, 모집중, 종료)
    private SessionStatus sessionStatus;

    //무료인지 유료인지
    private SessionType sessionType;

    private List<NsUser> users = new ArrayList<>();

    private Integer maxNoOfUsers;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Payment payment;

    protected Session() {
    }

    public Session(List<NsUser> users, SessionType type, SessionStatus sessionStatus, Integer maxNoOfUsers) {
        this.users = users;
        this.sessionType = type;
        this.sessionStatus = sessionStatus;
        this.maxNoOfUsers = maxNoOfUsers;
    }

    public Session(long id,
                   String sessionName,
                   Image image,
                   SessionStatus sessionStatus,
                   SessionType sessionType) {
        this.id = id;
        this.sessionName = sessionName;
        this.image = image;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
    }

    public boolean isRecruitingFreeSession() {
        return isFreeCourse() && isRecruiting();
    }

    public boolean isRecruiting() {
        return sessionStatus == SessionStatus.RECRUITING;
    }

    private boolean isFreeCourse() {
        return sessionType == SessionType.FREE;
    }

    public void validateUserLimitForPaidCourse() {
        if (isPaidCourse() && isExceedUserNo()) {
            throw new IllegalArgumentException("수강인원을 초과하였습니다.");
        }
    }

    private boolean isPaidCourse() {
        return sessionType == SessionType.PAID;
    }

    private boolean isExceedUserNo() {
        return users.size() >= maxNoOfUsers;
    }

}

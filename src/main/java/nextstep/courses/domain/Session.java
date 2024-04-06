package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private long id;

    private String sessionName;

    private long price;

    private Image image;

    //강의 상태(준비중, 모집중, 종료)
    private SessionStatus sessionStatus;

    //무료인지 유료인지
    private UsageType usageType;

    private List<NsUser> users = new ArrayList<>();

    private Integer maxNoOfUsers;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Payment payment;

    protected Session() {
    }

    public Session(long id,
                   String sessionName,
                   long price,
                   Image image,
                   SessionStatus sessionStatus,
                   UsageType usageType) {
        this.id = id;
        this.sessionName = sessionName;
        this.price = price;
        this.image = image;
        this.sessionStatus = sessionStatus;
        this.usageType = usageType;
    }

    public Session(List<NsUser> users, UsageType type, SessionStatus sessionStatus, Integer maxNoOfUsers) {
        this.users = users;
        this.usageType = type;
        this.sessionStatus = sessionStatus;
        this.maxNoOfUsers = maxNoOfUsers;
    }

    public Session(List<NsUser> users, UsageType type, SessionStatus sessionStatus, Integer maxNoOfUsers, long price) {
        this(users, type, sessionStatus, maxNoOfUsers);
        this.price = price;
    }

    public boolean isRecruitingFreeSession() {
        return isFreeCourse() && isRecruiting();
    }

    public boolean isRecruiting() {
        return sessionStatus == SessionStatus.RECRUITING;
    }

    private boolean isFreeCourse() {
        return usageType == UsageType.FREE;
    }

    public void validateUserLimitForPaidCourse() {
        if (isPaidCourse() && isExceedUserNo()) {
            throw new IllegalArgumentException("수강인원을 초과하였습니다.");
        }
    }

    private boolean isPaidCourse() {
        return usageType == UsageType.PAY;
    }

    private boolean isExceedUserNo() {
        return users.size() >= maxNoOfUsers;
    }

    public long getPrice() {
        return price;
    }

}

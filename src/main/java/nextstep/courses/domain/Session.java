package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

    private long id;

    private String sessionName;

    //가격
    private int price;

    private Image image;

    //강의 상태(준비중, 모집중, 종료)
    private Status status;

    //무료인지 유료인지
    private UsageType usageType;

    private List<NsUser> users = new ArrayList<>();

    private Integer maxNoOfUsers;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    protected Session() {
    }

    public Session(long id,
                   String sessionName,
                   int price,
                   Image image,
                   Status status,
                   UsageType usageType) {
        this.id = id;
        this.sessionName = sessionName;
        this.price = price;
        this.image = image;
        this.status = status;
        this.usageType = usageType;
    }

    public Session(List<NsUser> users, UsageType type, Status status, Integer maxNoOfUsers) {
        this.users = users;
        this.usageType = type;
        this.status = status;
        this.maxNoOfUsers = maxNoOfUsers;
    }

    public boolean isRecruiting() {
        return status == Status.RECRUITING;
    }

    public void validateUserLimitForPaidCourse() {
        if (isPaidCourse() && isExceedUserNo()) {
            throw new IllegalArgumentException("수강인원을 초과하였습니다.");
        }
    }

    private boolean isExceedUserNo() {
        return users.size() == maxNoOfUsers;
    }

    private boolean isPaidCourse() {
        return usageType == UsageType.PAY;
    }

}

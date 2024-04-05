package nextstep.courses.domain;

import nextstep.courses.domain.image.Image;

public class Session {

    private long id;

    private String sessionName;

    private int price;

    private Image image;

    //강의 상태(준비중, 모집중, 종료)
    private Status status;

    //무료인지 유료인지
    private UsageType usageType;

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

    public boolean isRecruiting() {
        return status == Status.RECRUITING;
    }

}

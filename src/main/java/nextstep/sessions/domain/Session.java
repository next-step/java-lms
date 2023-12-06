package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

    // id
    private long id;

    // 강의 이름
    private String name;

    // 날짜
    private SessionDate date;
    // 이미지
    private SessionImage image;

    // 가격
    private SessionCharge charge;

    // 상태
    private SessionStatus status;

    public Session(String name, SessionDate date, SessionImage image, SessionCharge charge, SessionStatus status) {
        this.id = 0l;
        this.name = name;
        this.date = date;
        this.image = image;
        this.charge = charge;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SessionDate getDate() {
        return date;
    }

    public SessionCharge getCharge() {
        return charge;
    }

    public void checkSessionStatus() {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 수강할 수 있습니다.");
        }
    }

    public void addStudent() {
        checkSessionStatus();
        this.charge.addStudent();
    }


}

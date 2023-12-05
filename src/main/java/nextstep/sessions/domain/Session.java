package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

    // id
    private long id;

    // 강의 이름
    private String name;

    // 시작일
    private LocalDate startAt;

    // 종료일
    private LocalDate endAt;

    // 이미지
    private SessionImage image;

    // 가격
    private SessionCharge charge;

    // 상태
    private SessionStatus status;

    public Session(String name, LocalDate startAt, LocalDate endAt, SessionImage image, SessionCharge charge, SessionStatus status) {
        this.id = 0l;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
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

    public LocalDate getStartAt() {
        return startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public SessionCharge getCharge() {
        return charge;
    }

    public void check() {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중인 강의만 수강할 수 있습니다.");
        }
    }

    public void addStudent() {
        check();
        this.charge.addStudent();
    }


}

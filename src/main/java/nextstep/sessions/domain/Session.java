package nextstep.sessions.domain;

import java.time.LocalDate;

public class Session {

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

    public Session(LocalDate startAt, LocalDate endAt, SessionImage image, SessionCharge charge, SessionStatus status) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.image = image;
        this.charge = charge;
        this.status = status;
    }
}

package nextstep.sessions.domain;

import nextstep.imgae.domain.Image;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {
    private final Long id;
    private SessionPeriod sessionPeriod;
    private SessionState sessionState;
    private Enrollment enrollment;
    private Image image;
    private boolean isFree;
    private Long teacher;
    private String title;

    public Session(Long id, LocalDateTime startDay, LocalDateTime endDay, SessionState sessionState,
                   Image image, boolean isFree, NsUser teacher, String title, Enrollment enrollment) {
        this.id = id;
        this.sessionPeriod = new SessionPeriod(startDay, endDay);
        this.sessionState = sessionState;
        this.image = image;
        this.isFree = isFree;
        this.enrollment = enrollment;
        this.teacher = teacher.getId();
        this.title = title;
    }

    public void enroll(NsUser stdent, int payMoney) {
        this.enrollment.enroll(stdent, payMoney);
    }
}

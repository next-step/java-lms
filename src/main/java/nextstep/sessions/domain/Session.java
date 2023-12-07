package nextstep.sessions.domain;

import nextstep.imgae.domain.Image;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final Long id;
    private SessionPeriod startDay;
    private SessionPeriod endDay;
    private SessionState sessionState;
    private Image image;
    private boolean isFree;
    private Long cost;
    private List<Long> studentIds;
    private Long teacher;
    private String title;

    public Session(Long id, LocalDateTime startDay, LocalDateTime endDay, SessionState sessionState, Image image, boolean isFree, Long cost, Long teacher, String title) {
        this.id = id;
        this.startDay = new SessionPeriod(startDay);
        this.endDay = new SessionPeriod(endDay);
        this.sessionState = sessionState;
        this.image = image;
        this.isFree = isFree;
        this.cost = cost;
        this.studentIds = new ArrayList<>();
        this.teacher = teacher;
        this.title = title;
    }

    public void validateEnrollmentSessionState() {
        if (this.sessionState != SessionState.RECRUIT) {
            throw new IllegalArgumentException("강의가 모집중이 아닙니다.");
        }
    }
}

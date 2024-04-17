package nextstep.courses.domain;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Course course;
    private SessionImage sessionImage;
    private SessionStatus sessionStatus;
    private SessionType sessionType;
    private int capacity;
    private Long fee;
    private List<NsUser> students = new ArrayList<>();

    public Session(
            Course course,
            SessionImage sessionImage,
            LocalDateTime startTime,
            LocalDateTime endTime,
            SessionStatus sessionStatus,
            SessionType sessionType,
            int capacity,
            Long fee
    ) {
        this.course = course;
        this.sessionImage = sessionImage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
        this.capacity = capacity;
        this.fee = fee;
    }
}

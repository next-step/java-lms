package nextstep.courses.domain;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Course course;
    private SessionImage sessionImage;
    private SessionStatus sessionStatus;
    private SessionType sessionType;
    private int capacity;
    private Long fee;
    private List<NsUser> students = new ArrayList<>();
}

package nextstep.courses.domain.session;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.constant.SessionType;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.SessionImage;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final Course course;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final SessionImage sessionImage;
    private final SessionStatus sessionStatus;
    private final SessionType sessionType;
    private final List<NsUser> students = new ArrayList<>();

    protected Session(
            Course course,
            SessionImage sessionImage,
            LocalDateTime startTime,
            LocalDateTime endTime,
            SessionStatus sessionStatus,
            SessionType sessionType
    ) {
        this.course = course;
        this.sessionImage = sessionImage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionStatus = sessionStatus;
        this.sessionType = sessionType;
    }

    protected void validateEnrollSessionStatus() {
        if (!sessionStatus.equals(SessionStatus.RECRUITING)) {
            throw new IllegalArgumentException("수강 모집중인 세션이 아닙니다.");
        }
    }

    protected int getEnrolledStudentCount() {
        return students.size();
    }

    protected void enrollStudent(NsUser nsUser) {
        students.add(nsUser);
    }
}

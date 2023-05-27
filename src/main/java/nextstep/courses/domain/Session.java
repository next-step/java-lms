package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Session {

    private final SessionInfo sessionInfo;
    private final SessionStatus status;
    private final SessionTimeLine sessionTimeLine;
    private final Set<Student> students = new HashSet<>();
    private final Long maxNumOfStudent;

    public Session(Long id, String title, String coverImageInfo,
                   SessionType sessionType, SessionStatus sessionStatus,
                   LocalDateTime createdAt, LocalDateTime closedAt, Long maxNumOfStudent) {
        this(
                new SessionInfo(id, title, coverImageInfo, sessionType),
                sessionStatus,
                new SessionTimeLine(createdAt, closedAt),
                maxNumOfStudent
        );
    }

    public Session(SessionInfo sessionInfo, SessionStatus status, SessionTimeLine sessionTimeLine, Long maxNumOfStudent) {
        this.sessionInfo = sessionInfo;
        this.status = status;
        this.sessionTimeLine = sessionTimeLine;
        this.maxNumOfStudent = maxNumOfStudent;
    }

    public void add(Student student) {
        if (isStudentFull()) {
            throw new CannotEnrollException("강의 수강 신청 인원이 다 찼습니다!");
        }
        if (!this.status.canJoin()) {
            throw new CannotEnrollException("모집 중일때만 신청 가능합니다!");
        }
        students.add(student);
    }

    private boolean isStudentFull() {
        return this.maxNumOfStudent <= this.students.size();
    }

    public int totalStudentNum() {
        return students.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(sessionInfo, session.sessionInfo) && Objects.equals(sessionTimeLine, session.sessionTimeLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionInfo, sessionTimeLine);
    }
}

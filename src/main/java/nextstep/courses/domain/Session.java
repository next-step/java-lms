package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Session {
    private final SessionInfo sessionInfo;
    private final SessionStatus sessionStatus;
    private final SessionTimeLine sessionTimeLine;
    private final Set<Student> students = new HashSet<>();
    private final Long maxNumOfStudent;

    public Session(Long courseId, Long id, String title, String coverImageInfo,
                   SessionType sessionType, SessionProgressStatus sessionProgressStatus, SessionRecruitmentStatus sessionRecruitmentStatus,
                   LocalDateTime createdAt, LocalDateTime closedAt, Long maxNumOfStudent) {

        this(
                new SessionInfo(courseId, id, title, coverImageInfo, sessionType),
                new SessionStatus(sessionRecruitmentStatus, sessionProgressStatus),
                new SessionTimeLine(createdAt, closedAt),
                maxNumOfStudent
        );
    }

    public Session(SessionInfo sessionInfo, SessionStatus sessionStatus, SessionTimeLine sessionTimeLine, Long maxNumOfStudent) {
        this.sessionInfo = sessionInfo;
        this.sessionStatus = sessionStatus;
        this.sessionTimeLine = sessionTimeLine;
        this.maxNumOfStudent = maxNumOfStudent;
    }

    public void add(Student student) {
        if (isStudentFull()) {
            throw new CannotEnrollException("강의 수강 신청 인원이 다 찼습니다!");
        }
        if (!this.sessionStatus.canJoin()) {
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

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public SessionStatus getStatus() {
        return sessionStatus;
    }

    public SessionTimeLine getSessionTimeLine() {
        return sessionTimeLine;
    }


    public Long getMaxNumOfStudent() {
        return maxNumOfStudent;
    }
}

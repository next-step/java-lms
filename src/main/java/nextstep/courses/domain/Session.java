package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Session {


    private final SessionInfo sessionInfo;
    private final SessionStatus sessionStatus;
    private final SessionTimeLine sessionTimeLine;
    private final Set<Student> students = new HashSet<>();
    private final Long maxNumberOfStudent;

    public Session(Long courseId, Long ownerId, String title, String coverImageInfo,
                   SessionType sessionType, SessionStatus sessionStatus,
                   LocalDateTime createdAt, LocalDateTime closedAt, Long maxNumOfStudent) {

            this(new SessionInfo(courseId, ownerId, title, coverImageInfo, sessionType),
                sessionStatus,
                new SessionTimeLine(createdAt, closedAt),
                maxNumOfStudent
        );
    }

    public Session(SessionInfo sessionInfo, SessionStatus sessionStatus,
                   SessionTimeLine sessionTimeLine, Long maxNumberOfStudent){
        this.sessionInfo = sessionInfo;
        this.sessionStatus = sessionStatus;
        this.sessionTimeLine = sessionTimeLine;
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public void add(Student student) {
        if (!sessionStatus.canJoin()) {
            throw new CannotEnrollException("현재는 수강신청을 할 수 없는 강의 상태입니다. 현재 강의 상태 = " + sessionStatus.name());
        }

        if (isPositionFull()) {
            throw new CannotEnrollException(
                    "현재 강의(Session)는 수강인원이 꽉 차서 더 이상 등록할 수 없습니다." + "최대인원 = " + maxNumberOfStudent);
        }
        this.students.add(student);
    }

    public int totalStudentNum() {
        return students.size();
    }

    private boolean isPositionFull() {
        return totalStudentNum() == maxNumberOfStudent;
    }
}

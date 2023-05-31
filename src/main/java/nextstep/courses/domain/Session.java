package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String coverImageUrl;
    private final SessionType type;
    private final SessionStatus status;
    private final List<Student> students;

    private Session(LocalDateTime startDate, LocalDateTime endDate, String coverImageUrl,
                    SessionType type, SessionStatus status, List<Student> students) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImageUrl = coverImageUrl;
        this.type = type;
        this.status = status;
        this.students = students;
    }

    public static Session of(LocalDateTime startDate, LocalDateTime endDate, String coverImageUrl,
                             SessionType type, SessionStatus status, List<Student> students) {
        return new Session(startDate, endDate, coverImageUrl, type, status, students);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public SessionType getType() {
        return type;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public boolean enroll() throws SessionNotOpenedException, SessionMaxStudentsExceedException {
        if (status != SessionStatus.OPENED) {
            throw new SessionNotOpenedException("모집중인 강의가 아닙니다.");
        }
        if (students.size() >= 50) {
            throw new SessionMaxStudentsExceedException("수강 최대 인원을 초과했습니다.");
        }
        students.add(new Student());
        return true;
    }
}

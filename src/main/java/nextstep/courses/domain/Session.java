package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static nextstep.courses.domain.SessionStatus.OPENED;

public class Session {

    private final Set<Student> students = new HashSet<>();
    private String title;
    private Long maxNumOfStudent;
    private SessionStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private String coverImageInfo;
    private SessionType type;

    public Session() {

    }

    public void add(Student student) {
        if (this.maxNumOfStudent <= this.students.size()) {
            throw new CannotEnrollException("강의 수강 신청 인원이 다 찼습니다!");
        }
        if (this.status != OPENED) {
            throw new CannotEnrollException("모집 중일때만 신청 가능합니다!");
        }
        students.add(student);
    }

    public int totalStudentNum() {
        return students.size();
    }

    public void setMaxNumOfStudent(Long maxNumOfStudent) {
        this.maxNumOfStudent = maxNumOfStudent;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(students, session.students) && Objects.equals(title, session.title) && Objects.equals(maxNumOfStudent, session.maxNumOfStudent) && status == session.status && Objects.equals(createdAt, session.createdAt) && Objects.equals(closedAt, session.closedAt) && Objects.equals(coverImageInfo, session.coverImageInfo) && type == session.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, title, maxNumOfStudent, status, createdAt, closedAt, coverImageInfo, type);
    }
}

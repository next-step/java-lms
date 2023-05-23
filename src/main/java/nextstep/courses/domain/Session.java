package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String coverImage;
    private PaymentType paymentType;
    private SessionStatus sessionStatus;

    private List<Student> students = new ArrayList<>();
    private int maxStudentSize;

    public Session(Long id, LocalDateTime startAt, LocalDateTime endAt, String coverImage, PaymentType paymentType, SessionStatus sessionStatus, int maxStudentSize) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException("시작일은 종료일을 넘을 수 없습니다.");
        }
        this.id = id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.sessionStatus = sessionStatus;
        this.maxStudentSize = maxStudentSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return maxStudentSize == session.maxStudentSize && Objects.equals(id, session.id) && Objects.equals(startAt, session.startAt) && Objects.equals(endAt, session.endAt) && Objects.equals(coverImage, session.coverImage) && paymentType == session.paymentType && sessionStatus == session.sessionStatus && Objects.equals(students, session.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startAt, endAt, coverImage, paymentType, sessionStatus, students, maxStudentSize);
    }

    public void register(Student student) {
        validate();
        students.add(student);
    }

    private void validate() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중이 아닙니다.");
        }
        if (maxStudentSize <= students.size()) {
            throw new IllegalStateException("모집인원이 가득 찼습니다.");
        }
    }
}

package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private Long courseId;
    private SessionInfo sessionInfo;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Price price;
    private Status status;
    private Students students;

    public Session(Long id,
                   Long courseId,
                   SessionInfo sessionInfo,
                   LocalDateTime startedAt,
                   LocalDateTime endedAt,
                   Price price,
                   Status status,
                   Students students) {
        this.id = id;
        this.courseId = courseId;
        this.sessionInfo = sessionInfo;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.price = price;
        this.status = status;
        this.students = students;
    }

    public void changeStatue(Status status) {
        this.status = status;
    }
    public void enroll(Student student) throws CannotEnrollException {
        status.confirmRecruiting();
        students.addStudent(student);
    }

}

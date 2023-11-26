package nextstep.courses.domain;

public interface Enrollment {
    void enroll(long payment,
                Student student,
                Students students);
}

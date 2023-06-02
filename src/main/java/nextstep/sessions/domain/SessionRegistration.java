package nextstep.sessions.domain;

import nextstep.sessions.ExceedingMaximumStudentException;
import nextstep.sessions.NotEligibleRegistrationStatusException;
import nextstep.students.domain.Student;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class SessionRegistration {

    private final SessionStatus status;
    private final Students students;
    private final int studentCapacity;

    public SessionRegistration(SessionStatus status, Students students, int studentCapacity) {
        this.status = status;
        this.students = students;
        this.studentCapacity = studentCapacity;
    }

    public void register(NsUser student, Session session) {
        validate();
        students.add(student.getUserId(), session.getId());
    }

    public Student enrolledStudent(NsUser student) {
        return students.find(student.getUserId());
    }

    private void validate() {
        if (!status.isRecruiting()) {
            throw new NotEligibleRegistrationStatusException();
        }
        if (students.size() >= studentCapacity) {
            throw new ExceedingMaximumStudentException();
        }
    }

    public SessionStatus getStatus() {
        return status;
    }

    public String getStatusName() {
        return status.getName();
    }

    public int getStudentCapacity() {
        return studentCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionRegistration that = (SessionRegistration) o;
        return studentCapacity == that.studentCapacity && status == that.status && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, students, studentCapacity);
    }

    @Override
    public String toString() {
        return "SessionRegistration{" +
                "status=" + status +
                ", students=" + students +
                ", studentCapacity=" + studentCapacity +
                '}';
    }

}

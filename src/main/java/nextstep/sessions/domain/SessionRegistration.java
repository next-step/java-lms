package nextstep.sessions.domain;

import nextstep.sessions.ExceedingMaximumStudentException;
import nextstep.sessions.NotEligibleRegistrationStatusException;
import nextstep.students.domain.Student;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUser;

import java.util.List;
import java.util.Objects;

public class SessionRegistration {

    private final SessionStatus status;
    private final SessionRecruitmentStatus recruitmentStatus;
    private final Students students;
    private final int studentCapacity;

    public SessionRegistration(SessionStatus status, SessionRecruitmentStatus recruitmentStatus, Students students, int studentCapacity) {
        this.status = status;
        this.recruitmentStatus = recruitmentStatus;
        this.students = students;
        this.studentCapacity = studentCapacity;
    }

    public void register(NsUser student, Session session) {
        validate();
        students.add(student.getUserId(), session.getId());
    }

    private void validate() {
        if (!status.isRegistrable() || !recruitmentStatus.isRecruiting()) {
            throw new NotEligibleRegistrationStatusException();
        }
        if (students.size() >= studentCapacity) {
            throw new ExceedingMaximumStudentException();
        }
    }

    public Student enrolledStudent(NsUser student) {
        return students.find(student.getUserId());
    }

    public void addAll(List<Student> appliedStudents) {
        students.addAll(appliedStudents);
    }

    public SessionStatus getStatus() {
        return status;
    }

    public String getStatusName() {
        return status.getName();
    }

    public SessionRecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public String getRecruitmentStatusName() {
        return recruitmentStatus.getName();
    }

    public int getStudentCapacity() {
        return studentCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionRegistration that = (SessionRegistration) o;
        return studentCapacity == that.studentCapacity
                && status == that.status
                && recruitmentStatus == that.recruitmentStatus
                && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, recruitmentStatus, students, studentCapacity);
    }

    @Override
    public String toString() {
        return "SessionRegistration{" +
                "status=" + status +
                ", recruitmentStatus=" + recruitmentStatus +
                ", students=" + students +
                ", studentCapacity=" + studentCapacity +
                '}';
    }
}

package nextstep.sessions.domain;

import nextstep.sessions.ExceedingMaximumStudentException;
import nextstep.sessions.NotEligibleRegistrationStatusException;
import nextstep.students.domain.Student;
import nextstep.students.domain.Students;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class SessionRegistration {

    private final SessionStatus status;
    private final SessionRecruitmentStatus recruitmentStatus;
    private final Students students;
    private final int studentCapacity;

    public SessionRegistration(SessionStatus status, SessionRecruitmentStatus recruitmentStatus, Students students, int studentCapacity) {
        if (status.isAsisRecruiting()) {
            status = SessionStatus.PREPARING;
            recruitmentStatus = SessionRecruitmentStatus.RECRUITING;
        }
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
        if (!isRegistrable()) {
            throw new NotEligibleRegistrationStatusException();
        }
        if (students.size() >= studentCapacity) {
            throw new ExceedingMaximumStudentException();
        }
    }

    private boolean isRegistrable() {
        return status.isRegistrable() && recruitmentStatus.isRecruiting();
    }

    public Student enrolledStudent(NsUser student) {
        return students.find(student.getUserId());
    }

    public void validateApprovalOrRejected() {
        if (!isRegistrable()) {
            throw new IllegalStateException("수강신청이 완료되어 수정이 불가합니다.");
        }
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

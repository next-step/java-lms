package nextstep.session.domain.student;

import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.session.SessionType;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class EnrolledStudents {

    private Long sessionId;
    private List<EnrolledStudent> students;

    public EnrolledStudents() {
        this(0L, new ArrayList<>());
    }

    public EnrolledStudents(Long sessionId, List<EnrolledStudent> students) {
        this.sessionId = sessionId;
        this.students = (students == null) ? new ArrayList<>() : students;
    }


    public void enrollWithPolicyCheck(PaymentPolicy policy, NsUser user, SessionType sessionType) {
        if ( ! policy.canEnroll(count()) ) {
            throw new IllegalStateException("더이상 학생을 추가할 수 없습니다. 최대 학생수=" + policy.enrollmentLimit() + ", 현재 학생수=" + count());
        }

        EnrollmentStatus status = (sessionType == SessionType.AUTO_APPROVAL)
                ? EnrollmentStatus.APPROVED
                : EnrollmentStatus.WAITING;
        students.add(new EnrolledStudent(user.getId(), status));
    }

    public int count() {
        return students.size();
    }

    public Long getSessionId() {
        return sessionId;
    }
    public List<EnrolledStudent> getStudents() {
        return students;
    }

    public void approveEnrollment(Long studentId) {
        for (EnrolledStudent student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.approve();
                return;
            }
        }
        throw new IllegalStateException("수강신청한 학생이 아닙니다.");
    }

    public void rejectEnrollment(Long studentId) {
        for (EnrolledStudent student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.reject();
                return;
            }
        }
        throw new IllegalStateException("수강신청한 학생이 아닙니다.");
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    public int size() {
        return students.size();
    }
}

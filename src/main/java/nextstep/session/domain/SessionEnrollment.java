package nextstep.session.domain;

import nextstep.session.CannotEnrollException;
import nextstep.users.domain.NsUser;

public class SessionEnrollment {

    private EnrollmentStatus enrollmentStatus;
    private EnrollmentPolicy enrollmentPolicy;
    private Students students;

    public SessionEnrollment(EnrollmentStatus enrollmentStatus, EnrollmentPolicy enrollmentPolicy,
        Students students) {
        this.enrollmentStatus = enrollmentStatus;
        this.enrollmentPolicy = enrollmentPolicy;
        this.students = students;
    }

    private boolean canEnroll(int payment) {
        if (enrollmentPolicy.isCapacityFull(students.enrolledStudentCount())) {
            throw new CannotEnrollException("수강 인원 초과로 인해 현재 이 강의에 추가 등록이 불가능합니다.");
        }
        if (!enrollmentStatus.isSessionOpenForRegistration()) {
            throw new CannotEnrollException("현재 모집중인 강의가 아닙니다.");
        }
        if (!enrollmentPolicy.isPaymentCorrect(payment)) {
            throw new CannotEnrollException("수강료와 지불금액이 일치하지 않습니다.");
        }
        return true;
    }

    public Student enroll(NsUser user, int payment, Long sessionId) {
        canEnroll(payment);
        Student student = new Student(user.getId(), sessionId);
        students.add(student);
        return student;
    }

    public void approvalStudent(Student student) {
        this.students.approval(student);
    }

    public void cancelStudent(Student student) {
        this.students.cancel(student);
    }

    public int enrolledStudentCount() {
        return students.enrolledStudentCount();
    }

    public void changeEnrollmentStatusOpen() {
        this.enrollmentStatus = EnrollmentStatus.OPEN;
    }

    public void changeEnrollmentStatusClosed() {
        this.enrollmentStatus = EnrollmentStatus.CLOSED;
    }

    public String getPriceType() {
        return enrollmentPolicy.getPriceType();
    }

    public int getMaxEnrollment() {
        return enrollmentPolicy.getMaxEnrollment();
    }

    public int getFee() {
        return enrollmentPolicy.getFee();
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus.getDescription();
    }
}

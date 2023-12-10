package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;

public class Student {
    private final NsUser student;
    private SessionApproval sessionApproval;

    public Student(NsUser student, SessionApproval sessionApproval) {
        this.student = student;
        this.sessionApproval = sessionApproval;
    }

    public static Student ofWait(NsUser student) {
        return new Student(student, SessionApproval.WAIT);
    }

    public static Student of(NsUser student, String sessionApproval) {
        return new Student(student, SessionApproval.valueOf(sessionApproval));
    }

    public static Student of(NsUser student, SessionApproval sessionApproval) {
        return new Student(student, sessionApproval);
    }

    public void approval() {
        sessionApproval = SessionApproval.APPROVAL;
    }

    public void cancel() {
        sessionApproval = SessionApproval.CANCEL;
    }

    public boolean sameUser(NsUser nsUser) {
        return userId().equals(nsUser.getUserId());
    }

    public SessionApproval sessionApproval() {
        return sessionApproval;
    }

    public boolean isWait() {
        return sessionApproval.isWait();
    }

    public String SessionApprovalValue() {
        return sessionApproval.name();
    }

    public String userId() {
        return student.getUserId();
    }


    @Override
    public String toString() {
        return "Student{" +
                "student=" + student +
                ", sessionApproval=" + sessionApproval +
                '}';
    }
}


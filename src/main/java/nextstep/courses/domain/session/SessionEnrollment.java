package nextstep.courses.domain.session;


import java.util.HashSet;
import java.util.Set;

public class SessionEnrollment {

    private final ProgressStatus progressStatus;
    private final RecruitmentStatus recruitmentStatus;
    private final EnrolledStudents enrolledStudents;

    private SessionEnrollment(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.enrolledStudents = EnrolledStudents.of(new HashSet<>());
    }

    private SessionEnrollment(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, EnrolledStudents enrolledStudents) {
        this.progressStatus = progressStatus;
        this.recruitmentStatus = recruitmentStatus;
        this.enrolledStudents = enrolledStudents;
    }

    public static SessionEnrollment of(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        return new SessionEnrollment(progressStatus, recruitmentStatus);
    }

    public static SessionEnrollment of(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, EnrolledStudents enrolledUsers) {
        return new SessionEnrollment(progressStatus, recruitmentStatus, enrolledUsers);
    }

    public void enrollStudent(Student student) {
        validateDuplicateEnrollment(student);

        enrolledStudents.add(student);
    }

    public void validateDuplicateEnrollment(Student student) {
        if (isDuplicateEnrolledStudent(student)) {
            throw new IllegalStateException("중복된 수강신청입니다.");
        }
    }

    private boolean isDuplicateEnrolledStudent(Student student) {
        return enrolledStudents.contains(student);
    }

    public void approveStudent(Student student) {
        findEnrolledStudents(student).approve();
    }

    public void rejectStudent(Student student) {
        findEnrolledStudents(student).reject();
    }

    private Student findEnrolledStudents(Student student) {
        return enrolledStudents.getEnrolledStudents().stream()
                .filter(enrolledStudent -> enrolledStudent.matchStudents(student))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("수강신청하지 않은 수강생입니다."));
    }

    public int size() {
        return enrolledStudents.size();
    }

    public boolean isEnrollmentFull(int maxEnrollments) {
        return enrolledStudents.size() >= maxEnrollments;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents.getEnrolledStudents();
    }

    public boolean isNotInProgress() {
        return progressStatus.isNotInProgress();
    }

    public boolean isNotRecruiting() {
        return recruitmentStatus.isNotRecruiting();
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return recruitmentStatus;
    }

}

package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.common.SystemTimeStamp;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.image.SessionImage;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Session {
    private SessionInfo sessionInfo;
    private List<SessionImage> sessionImage;
    private List<Student> students;
    private SessionPlan sessionPlan;
    private SystemTimeStamp systemTimeStamp;

    public Session(SessionInfo sessionInfo, SessionPlan sessionPlan, SystemTimeStamp systemTimeStamp) {
        this.sessionInfo = sessionInfo;
        this.students = new ArrayList<>(Collections.emptyList());
        this.sessionPlan = sessionPlan;
        this.sessionImage = new ArrayList<>(Collections.emptyList());;
        this.systemTimeStamp = systemTimeStamp;
    }

    public void signUp(NsUser student) {
        Student studentInfo = new Student(student.getId(), this.getId(), RegistrationState.PENDING);
        validateEnrollmentStatus();
        students.add(studentInfo);
    }

    private void validateEnrollmentStatus() {
        if (!EnrollmentStatus.canSignUp(this.sessionPlan.getEnrollmentStatus())) {
            throw new CannotSignUpException("강의 모집중이 아닙니다.");
        }
    }

    public void saveImage(SessionImage sessionImage) {
        this.sessionImage.add(sessionImage);
    }

    public void cancelStudent(Student student) {
        Student validateStudent = validateIsAStudent(student);
        validateStudent.cancel();
    }

    public void approveStudent(Student student) {
        Student validatedStudent = validateIsAStudent(student);
        validatedStudent.approve();
    }

    private Student validateIsAStudent(Student student) {
        return this.getAllStudents().stream()
                .filter(x -> x.getNsUserId() == student.getNsUserId())
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public int getStudentCount() {
        return students.size();
    }

    public Long getId() {
        return sessionInfo.getId();
    }

    public long getCourseId() {
        return sessionInfo.getCourseId();
    }

    public String getTitle() {
        return sessionInfo.getTitle();
    }

    public SessionType getSessionType() {
        return sessionInfo.getSessionType();
    }

    public List<Student> getStudents() {
        return students.stream()
                .filter(student -> student.getRegistrationState() == RegistrationState.APPROVED)
                .collect(Collectors.toList());
    }
    public List<Student> getAllStudents() {
        return students;
    }

    public SessionPlan getSessionPlan() {
        return sessionPlan;
    }

    public SystemTimeStamp getSystemTimeStamp() {
        return systemTimeStamp;
    }

    public int getImageCount() {
        return sessionImage.size();
    }

    public boolean isFree() {
        return this.getSessionType().isFree();
    }
    public boolean isPaid() {
        return this.getSessionType().isPaid();
    }
}

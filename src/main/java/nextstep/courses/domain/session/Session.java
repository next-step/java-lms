package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.common.SystemTimeStamp;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.image.SessionImage;
import nextstep.qna.NotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void signUp(Student student) {
        validateEnrollmentStatus();
        students.add(student);
    }

    private void validateEnrollmentStatus() {
        if (!EnrollmentStatus.canSignUp(this.sessionPlan.getEnrollmentStatus())) {
            throw new CannotSignUpException("강의 모집중이 아닙니다.");
        }
    }

    public void saveImage(SessionImage sessionImage) {
        this.sessionImage.add(sessionImage);
    }

    public void cancelSession(Student student) {
        validateIsAStudent(student);
        student.isCanceled();
    }

    private void validateIsAStudent(Student student) {
        this.getStudents().stream()
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
}
